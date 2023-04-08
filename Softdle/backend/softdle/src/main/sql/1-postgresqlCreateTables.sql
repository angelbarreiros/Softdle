CREATE TYPE TypingType AS ENUM ('DYNAMIC','STATIC','BOTH','NO');
CREATE TYPE CompilingType AS ENUM ('COMPILED','INTERPRETED','BOTH','NO');
CREATE TYPE Paradigm AS ENUM ('FUNCTIONAL','OOP','MULTIPARADIGM','IMPERATIVE','PROCEDURAL');
CREATE TYPE RoleType AS ENUM ('USER', 'ADMIN');


CREATE TABLE language_ (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL UNIQUE,
                                    typeType TypingType,
                                    compilingType CompilingType,
                                    date  smallint check(
                                        date between 0 and extract(year from current_date)
                                        ),
                                    creator VARCHAR(255),
                                    paradigm Paradigm,
                                    purpose TEXT,
                                    numberOfJobs INTEGER,
                                    imagePath TEXT,
                                    isTheOne BOOLEAN NOT NULL
);
CREATE TABLE users (
                                    id SERIAL PRIMARY KEY,
                                    firstName VARCHAR(255) NOT NULL,
                                    lastName VARCHAR(255) NOT NULL,
                                    username VARCHAR(255) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    streak INTEGER NOT NULL DEFAULT 0,
                                    role RoleType NOT NULL
);
CREATE TABLE wins (
                                    id BIGSERIAL PRIMARY KEY,
                                    date DATE NOT NULL,
                                    numberOfAttempts INTEGER NOT NULL DEFAULT 0,
                                    user_id BIGINT NOT NULL,
                                    FOREIGN KEY (user_id) REFERENCES users(id)
);
