CREATE TABLE language_ (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL UNIQUE,
                                    typeType VARCHAR(255),
                                    compilingType VARCHAR(255),
                                    date  smallint check(
                                        date between 0 and extract(year from current_date)
                                        ),
                                    creator VARCHAR(255),
                                    paradigm VARCHAR(255),
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
                                    role VARCHAR(255) NOT NULL
);
CREATE TABLE wins (
                                    id BIGSERIAL PRIMARY KEY,
                                    date DATE NOT NULL,
                                    numberOfAttempts INTEGER NOT NULL DEFAULT 0,
                                    user_id BIGINT NOT NULL,
                                    FOREIGN KEY (user_id) REFERENCES users(id)
);