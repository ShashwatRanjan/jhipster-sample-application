package com.zigmoid.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.zigmoid.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.zigmoid.myapp.service..")
            .or()
            .resideInAnyPackage("com.zigmoid.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.zigmoid.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
