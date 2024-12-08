# file-utils
file utils 

## JetPack
- https://jitpack.io/
- https://jitpack.io/#givaraebo/file-utils/main-SNAPSHOT
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.givaraebo</groupId>
    <artifactId>file-utils</artifactId>
    <version>main-SNAPSHOT</version>
</dependency>
```


```java
CProperties properties = new CPropertiesImpl();
/**
 * get input directory
 */
System.out.println(properties.getInputDir());
System.out.println(properties.getOutputDir());
System.out.println(properties.filesGroupByFileType(DirType.INPUT));
System.out.println(properties.getDirectories(DirType.INPUT));

```
