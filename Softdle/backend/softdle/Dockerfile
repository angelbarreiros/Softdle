FROM postgres:latest

COPY src/main/sql/1-postgresqlCreateTables.sql /docker-entrypoint-initdb.d/ACreateTables.sql
COPY src/main/sql/2-postgresqlCreateData.sql /docker-entrypoint-initdb.d/BCreateData.sql

ENV POSTGRES_PASSWORD=sof
ENV POSTGRES_USER=sof
ENV POSTGRES_DB=softdleproject
