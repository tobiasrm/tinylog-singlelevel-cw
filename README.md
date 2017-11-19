# Tinylog Singlelevel CW
**A [Tinylog](http://www.tinylog.org) console writer extension to restrict writers to single log levels.**

Its main objective is a loglevel specific colored output. However, it is also helpful for debugging, e.g. only printing out trace logs and omitting the rest.

## Features
Singlelevel-CW extends the Tinylog `ConsoleWriter` with the `singlelevel-console` parameter to restrict log outputs to the corresponding level. Config definition: 

```  
 tinylog.writer              = singlelevel-console 
 tinylog.writer.singlelevel  = <true|false>
``` 

## Colored logs
With a witer being restricted to one level, you can colorize the log format (see [Tinylog Logging Format](http://www.tinylog.org/configuration#format)) of the level-specific writer using [ASCII escape codes](https://en.wikipedia.org/wiki/ANSI_escape_code). It gives you per-level flexibility (e.g. printing warnings in completely in yellow, errors in red). 

Singlelevel-cw comes with default colorization as shown below. See the corresponding [tinylog.properties](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/src/main/resources/tinylog.properties) as config example.

![](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/files/screenshot.png?raw=true)

You can reproduce the colored log demo by running `mvn clean install` and then `java -jar target/tinylog-singlelevel-1.3.1-executable.jar` (in an environment supporting colors via ACII espace characters, e.g. bash terminal).  
 
## Maven artifact
*in preparation*

## Comments
- **Patched Configuration class**. Singlelevel-CW implements the Tinylog Writer interface and is based on the Tinylog ConsoleWriter implementation. 
However, as the original tinylog `Configuration` does not provide the access on writer specific levels, a patch is provided with this project. The modifications are highlighted in the screenshots [one](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/files/configuration_mod_1.png?raw=true) and [two](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/files/configuration_mod_2.png?raw=true). You're invited to diff with the original version.. You're invited to diff with the original version.
- **Versioning**. The versioning (see [pom.xml](https://github.com/tobiasrm/tinylog-singlelevel-cw/blob/master/pom.xml)) uses the original Tinylog versions, e.g. singlelevel-cw in v1.3.1 uses Tinylog v1.3.1.


