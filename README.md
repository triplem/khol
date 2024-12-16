# khol

A pure kotlin implementation for the Holiday Calculation.

https://central.sonatype.org/publish/requirements/gpg/#signing-a-file


The following variable need to be set in github to be able to allow releasing to maven central (basically it should be
the gpg key used for signing):

*  export ORG_GRADLE_PROJECT_signingInMemoryKey

see https://vanniktech.github.io/gradle-maven-publish-plugin/central/#secrets and 
https://vanniktech.github.io/gradle-maven-publish-plugin/central/#in-memory-gpg-key for details.
