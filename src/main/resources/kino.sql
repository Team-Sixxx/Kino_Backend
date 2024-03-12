USE kinodb;


CREATE TABLE IF NOT EXISTS User (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS Membership (
    membershipId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    startDate DATE,
    endDate DATE,
    status ENUM('Active', 'Inactive', 'Cancelled') DEFAULT 'Inactive',
    FOREIGN KEY (userId) REFERENCES User(userId)
);


CREATE TABLE IF NOT EXISTS Preference (
    preferenceId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    category VARCHAR(50),
    FOREIGN KEY (userId) REFERENCES User(userId)
);


CREATE TABLE IF NOT EXISTS ViewingHistory (
    userId INT,
    filmId INT,
    dateWatched DATE,
    PRIMARY KEY (userId, filmId),
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (filmId) REFERENCES Film(filmId)
);


CREATE TABLE IF NOT EXISTS Film (
    filmId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    genre VARCHAR(100),
    ageRating INT,
    startTime DATETIME,
    endDate DATETIME,
    extraScreenings BOOLEAN,
    releaseDate DATE
);


CREATE TABLE IF NOT EXISTS Theater (
    theaterId INT AUTO_INCREMENT PRIMARY KEY,
    numberOfRows INT,
    seatsPerRow INT
);


CREATE TABLE IF NOT EXISTS Screening (
    screeningId INT AUTO_INCREMENT PRIMARY KEY,
    filmId INT,
    theaterId INT,
    startTime DATETIME,
    endTime DATETIME,
    numberOfSeats INT,
    FOREIGN KEY (filmId) REFERENCES Film(filmId),
    FOREIGN KEY (theaterId) REFERENCES Theater(theaterId)
);


CREATE TABLE IF NOT EXISTS Ticket (
    ticketId INT AUTO_INCREMENT PRIMARY KEY,
    screeningId INT,
    seatId INT,
    price DECIMAL(10,2),
    status ENUM('Available', 'Booked', 'Cancelled'),
    FOREIGN KEY (screeningId) REFERENCES Screening(screeningId),
    seatNumber VARCHAR(10)
);


CREATE TABLE IF NOT EXISTS ScreeningHistory (
    screeningId INT,
    theaterId INT,
    startTime DATETIME,
    endTime DATETIME,
    attendees INT,
    PRIMARY KEY (screeningId, theaterId),
    FOREIGN KEY (screeningId) REFERENCES Screening(screeningId),
    FOREIGN KEY (theaterId) REFERENCES Theater(theaterId)
);

CREATE TABLE IF NOT EXISTS Employee (
    employeeId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    password VARCHAR(100) NOT NULL
);


ALTER TABLE ViewingHistory
ADD CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES User(userId),
ADD CONSTRAINT fk_film FOREIGN KEY (filmId) REFERENCES Film(filmId);