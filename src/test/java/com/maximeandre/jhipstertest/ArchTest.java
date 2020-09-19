package com.maximeandre.jhipstertest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.maximeandre.jhipstertest");

        noClasses()
            .that()
                .resideInAnyPackage("com.maximeandre.jhipstertest.service..")
            .or()
                .resideInAnyPackage("com.maximeandre.jhipstertest.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.maximeandre.jhipstertest.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
