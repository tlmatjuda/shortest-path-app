-- Table to store information about planets
CREATE TABLE Planets
(
  Node VARCHAR(3) PRIMARY KEY,
  Name VARCHAR(100) UNIQUE NOT NULL
);

-- Table to store information about the routes or distances between planets
CREATE TABLE Routes
(
  Id          SERIAL PRIMARY KEY,
  Origin      VARCHAR(3) REFERENCES Planets (Node),
  Destination VARCHAR(3) REFERENCES Planets (Node),
  Distance    FLOAT NOT NULL
);
