#! /bin/bash
# A simple variable example
#greeting=Hello
#name=Tux
project='/home/investwell/code/InvestwellAutomation'
log_file='/home/investwell/code/InvestwellAutomation/script.log'

#echo $greeting $name

echo "Hello World" >> "${log_file}"
cd $project >> "${log_file}"
#echo $ls
PARAM1=$1
PARAM2=$2
echo "${PARAM1}" >>"${log_file}"
XMLFILE_PATH="XMLFiles/${PARAM2}.xml"
echo "$XMLFILE_PATH" >>"${log_file}"

echo $PWD >> "${log_file}"
chmod +x /home/investwell/code/InvestwellAutomation/script.sh
mvn clean install -Denvironment=${PARAM1} -DxmlFile=$XMLFILE_PATH

# Edit crontab:
#crontab -e							# open user crontab in edit mode
      								# for insert.  Make your changes
#*/1 * * * * sh /usr/local/htdocs/project/InvestwellAutomation/script.sh     	# plan it daily at 2am for example

#mvn clean install -Denvironment=uat -DxmlFile=testing.xml 
