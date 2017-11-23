# Tinylog Singlelevel CW
**A [Tinylog](http://www.tinylog.org) console writer extension to restrict writers to single log levels.**
 

## Features
Singlelevel-CW extends Tinylog with the `singlelevel-console` parameter to restrict log outputs to the corresponding level. This supports debugging by defining multiple writers and print exactly the log-levels you need (e.g. traces, warnings, and errors).


```   
   tinylog.writer              = singlelevel-console 
   tinylog.writer.singlelevel  = <true|false>
``` 

## Example
The following screenshot shows the example where messages are logged to all log-levels (see Main.java) but the singlelevel-cw prints only the log-levels trace, warn and error are printed (here in Eclipse using the 'err ' stream for warn/error). This is realized by defining multiple writers, one per log-level and all restricted to the corresponding level, but activating only the needed ones.
  
![](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/files/screenshot.png?raw=true)

You can reproduce the example by running `mvn clean install` and then `java -jar target/tinylog-singlelevel-cw-1.3.1-executable.jar`.
 
## Maven artifact
Add the following dependency to use the singlelevel-cw in your maven project:

```
<dependency>
	<groupId>com.github.tobiasrm</groupId>
	<artifactId>tinylog-singlelevel-cw</artifactId>
	<version>1.3.1</version>
</dependency>
```

## Comments
- **Versioning**. The versioning (see [pom.xml](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/pom.xml)) uses the original Tinylog versions, e.g. tinylog-singlelevel-cw in version 1.3.1 uses Tinylog v1.3.1. If needed, you may simply exclude it and use another Tinylog version.
- **Patched Configuration class**. Singlelevel-CW implements the Tinylog Writer interface and is based on the Tinylog ConsoleWriter implementation. 
However, as the original tinylog `Configuration` does not provide the access on writer specific levels, a patch is provided with this project. The modifications are highlighted in the screenshots [one](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/files/configuration_mod_1.png?raw=true) and [two](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/files/configuration_mod_2.png?raw=true). Of course, feel invited to diff with the original version. 

## Other Tinylog writer extensions
See also my other Tinylog writer extension projects:

-  [tinylog-coloredconsole](https://github.com/tobiasrm/tinylog-coloredconsole) for colored log-level and arbitrary source-code generated data through custom tags.
- [tinylog-tagging-filewriter](https://github.com/tobiasrm/tinylog-tagging-filewriter) extension to remove custom strings (e.g. the tinylog-coloredconsole custom tags) before writing to file (based on filewriter)
- [tinylog-tagging-rollingfilewriter](https://github.com/tobiasrm/tinylog-tagging-rollingfilewriter) extension to remove custom strings (e.g. the tinylog-coloredconsole custom tags) before writing to file (based on rollingfilewriter)

