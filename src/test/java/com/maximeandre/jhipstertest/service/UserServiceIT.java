package com.maximeandre.jhipstertest.service;

import com.maximeandre.jhipstertest.JhipstertestApp;
import com.maximeandre.jhipstertest.config.Constants;
import com.maximeandre.jhipstertest.domain.User;
import com.maximeandre.jhipstertest.repository.UserRepository;
import com.maximeandre.jhipstertest.service.dto.UserDTO;

import io.github.jhipster.security.RandomUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link UserService}.
 */
@SpringBootTest(classes = JhipstertestApp.class)
public class UserServiceIT {

    private static final String DEFAULT_LOGIN = "johndoe";

    private static final String DEFAULT_EMAIL = "johndoe@localhost";

    private static final String DEFAULT_FIRSTNAME = "john";

    private static final String DEFAULT_LASTNAME = "doe";

    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";

    private static final String DEFAULT_LANGKEY = "dummy";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void init() {
        userRepository.deleteAll();
        user = new User();
        user.setLogin(DEFAULT_LOGIN);
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail(DEFAULT_EMAIL);
        user.setFirstName(DEFAULT_FIRSTNAME);
        user.setLastName(DEFAULT_LASTNAME);
        user.setImageUrl(DEFAULT_IMAGEURL);
        user.setLangKey(DEFAULT_LANGKEY);
    }

    @Test
    public void assertThatUserMustExistToResetPassword() {
        userRepository.save(user);
        Optional<User> maybeUser = userService.requestPasswordReset("invalid.login@localhost");
        assertThat(maybeUser).isNotPresent();

        maybeUser = userService.requestPasswordReset(user.getEmail());
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.orElse(null).getEmail()).isEqualTo(user.getEmail());
        assertThat(maybeUser.orElse(null).getResetDate()).isNotNull();
        assertThat(maybeUser.orElse(null).getResetKey()).isNotNull();
    }

    @Test
    public void assertThatOnlyActivatedUserCanRequestPasswordReset() {
        user.setActivated(false);
        userRepository.save(user);

        Optional<User> maybeUser = userService.requestPasswordReset(user.getLogin());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    public void assertThatResetKeyMustNotBeOlderThan24Hours() {
        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        String resetKey = RandomUtil.generateResetKey();
        user.setActivated(true);
        user.setResetDate(daysAgo);
        user.setResetKey(resetKey);
        userRepository.save(user);

        Optional<User> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    public void assertThatResetKeyMustBeValid() {
        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        user.setActivated(true);
        user.setResetDate(daysAgo);
        user.setResetKey("1234");
        userRepository.save(user);

        Optional<User> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    public void assertThatUserCanResetPassword() {
        String oldPassword = user.getPassword();
        Instant daysAgo = Instant.now().minus(2, ChronoUnit.HOURS);
        String resetKey = RandomUtil.generateResetKey();
        user.setActivated(true);
        user.setResetDate(daysAgo);
        user.setResetKey(resetKey);
        userRepository.save(user);

        Optional<User> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.orElse(null).getResetDate()).isNull();
        assertThat(maybeUser.orElse(null).getResetKey()).isNull();
        assertThat(maybeUser.orElse(null).getPassword()).isNotEqualTo(oldPassword);

        userRepository.delete(user);
    }

    @Test
    public void assertThatNotActivatedUsersWithNotNullActivationKeyCreatedBefore3DaysAreDeleted() {
        Instant now = Instant.now();
        user.setActivated(false);
        user.setActivationKey(RandomStringUtils.random(20));
        User dbUser = userRepository.save(user);
        dbUser.setCreatedDate(now.minus(4, ChronoUnit.DAYS));
        userRepository.save(user);
        Instant threeDaysAgo = now.minus(3, ChronoUnit.DAYS);
        List<User> users = userRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(threeDaysAgo);
        assertThat(users).isNotEmpty();
        userService.removeNotActivatedUsers();
        users = userRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(threeDaysAgo);
        assertThat(users).isEmpty();
    }

    @Test
    public void assertThatNotActivatedUsersWithNullActivationKeyCreatedBefore3DaysAreNotDeleted() {
        Instant now = Instant.now();
        user.setActivated(false);
        User dbUser = userRepository.save(user);
        dbUser.setCreatedDate(now.minus(4, ChronoUnit.DAYS));
        userRepository.save(user);
        Instant threeDaysAgo = now.minus(3, ChronoUnit.DAYS);
        List<User> users = userRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(threeDaysAgo);
        assertThat(users).isEmpty();
        userService.removeNotActivatedUsers();
        Optional<User> maybeDbUser = userRepository.findById(dbUser.getId());
        assertThat(maybeDbUser).contains(dbUser);
    }

    @Test
    public void assertThatAnonymousUserIsNotGet() {
        user.setLogin(Constants.ANONYMOUS_USER);
        if (!userRepository.findOneByLogin(Constants.ANONYMOUS_USER).isPresent()) {
            userRepository.save(user);
        }
        final PageRequest pageable = PageRequest.of(0, (int) userRepository.count());
        final Page<UserDTO> allManagedUsers = userService.getAllManagedUsers(pageable);
        assertThat(allManagedUsers.getContent().stream()
            .noneMatch(user -> Constants.ANONYMOUS_USER.equals(user.getLogin())))
            .isTrue();
    }

}
