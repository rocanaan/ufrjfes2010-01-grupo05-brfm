Gerenciador de Projetos
====================




:::::::Usuario default da aplicacao:::::::

	Login: demo
	Senha: demo




:::::::Para rodar a aplicacao:::::::


Baixe:

	- JDK 6 (ja deve estar instalado... espero)
	- JBoss As 5.0.1
	- JBoss Seam 2.2.0
	- Apache Ant 1.7.0

Descompacte

Exporte as variaveis de ambiente de acordo com os diretórios de instalcao:


	(adicione em .bashrc em no diretorio home de seu usuario : ex: $vi .bashrc  )
	export JAVA_HOME=/usr/lib/jvm/java-6-openjdk
	export PATH=$PATH:$JAVA_HOME/bin

	export ANT_HOME=/home/user/JAVA/apache-ant-1.7.1
	export PATH=$PATH:$ANT_HOME/bin

	export JBOSS_HOME=/home/user/JAVA/jboss-5.0.1.GA
	export PATH=$PATH:$JBOSS_HOME/bin


Edite a linha abaixo do arquivo build2.xml de acordo com seu diretorio de instalacao do JBoss Seam:

	<!-- Location of Seam -->
	<dirname property="seam.dir" file="/home/senet/JAVA/jboss-seam-2.2.0.GA/bug" />
	
Rode como root o comando dentro do diretório do projeto em seu workspace:
	
	#ant clean && ant deploy
	
Inicie o servidor de aplicacao JBoss AS a partir de seu diretório bin:

	#./run.sh
	
No browser, coloque a url:

	http://localhost:8080/gerenciador-projetos

Duvidas: l.eau.de.vie.br@gmail.com

