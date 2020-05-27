# PdfAnalyzer

PdfAnalyzer allows to fetch all used fonts in pdf document.
Also the application allows to analyze font settings for every char in the pdf file.

## Description
History: in one of the projects we generated pdf report with embedded pdf font.
But in reality the font was not embedded properly, because the font used incorrect embedding property.

We did not find a free simple program to analyze all fonts and what font is used per char in pdf file.  

## Build

Clone and install <a href="https://github.com/StepanMelnik/Parent.git">Parent</a> project before building.

### Maven
> mvn clean install

### Docker
Check Header in the Dockerfile how to create and run an image of the project.

## Usage

Print help
> java -jar analyzer-0.0.0.Dev-SNAPSHOT.jar

Print all used fonts in console
> java -jar analyzer-0.0.0.Dev-SNAPSHOT.jar -source test.pdf -type FONT -report CONSOLE

Create test.html (as name of pdf file) report with all used fonts in pdf file
> java -jar analyzer-0.0.0.Dev-SNAPSHOT.jar -source test.pdf -type FONT -report HTML

Print all used chars with font settings per char in console
> java -jar analyzer-0.0.0.Dev-SNAPSHOT.jar -source test.pdf -type TEXT -report CONSOLE

Create test.html (as name of pdf file) report with all used chars and font settings per char in pdf file
> java -jar analyzer-0.0.0.Dev-SNAPSHOT.jar -source test.pdf -type TEXT -report HTML

Create test.html (as name of pdf file) report with all used chars. The chars have alternative info about font settings per char
> java -jar analyzer-0.0.0.Dev-SNAPSHOT.jar -source test.pdf -type TEXT -report TEXT_HTML

### Possible ssues 
- A large pdf document will have a lot of chars to be analyzed, so OutOfMemory may occur if java process does not have enough memory.
Right now the project does not support WeakReferences to consume as few memory as possible; 
- Parse specific pages only.

