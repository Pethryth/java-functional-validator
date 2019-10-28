# java-functional-validator

### How to add validator to maven project

- First step: add repository to pom.xml:

```
<repositories>
    <repository>
        <id>pethryth-repository</id>
        <name>pethryth-repository</name>
        <url>https://raw.githubusercontent.com/Pethryth/maven-repository/repository/</url>
    </repository>
</repositories>
```

- Second step: add dependency to pom.xml:

```
<dependency>
    <groupId>pl.wytworniakodu.commons</groupId>
    <artifactId>validator</artifactId>
    <version>1.0.0</version>
</dependency>
```
