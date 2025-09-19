ARG MAVEN_VERSION=3.9.11-eclipse-temurin-21
FROM maven:${MAVEN_VERSION}


ARG GECKODRIVER_VERSION=v0.36.0-linux64
# instructions de construction 
# ajouter des fichiers
# exécuter des commandes dans le système de ifchier de l'image
COPY ./amounts.csv /root/amounts.csv
COPY ./geckodriver-$GECKODRIVER_VERSION.tar.gz /root/geckodriver-$GECKODRIVER_VERSION.tar.gz
RUN tar \
      -xzf /root/geckodriver-$GECKODRIVER_VERSION.tar.gz \
      -C /usr/local/bin && \
      chmod u+x /usr/local/bin/geckodriver && \
      rm -f /root/geckodriver-$GECKODRIVER_VERSION.tar.gz
## l'état de l'image est maven + les fichiers + les droits modifiés
