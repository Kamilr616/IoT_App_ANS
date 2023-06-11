CREATE TABLE "Urzadzenie" (
  "id_urzadzenia" int PRIMARY KEY NOT NULL,
  "nazwa" varchar NOT NULL,
  "typ" varchar,
  "opis" varchar
);

CREATE TABLE "Czujnik" (
  "id_czujnika" int PRIMARY KEY NOT NULL,
  "id_urzadzenia" int NOT NULL,
  "typ" varchar,
  "opis" varchar,
  "jednostka" varchar
);

CREATE TABLE "Przekaznik" (
  "id_przekaznika" int PRIMARY KEY NOT NULL,
  "id_urzadzenia" int NOT NULL,
  "typ" varchar,
  "stan" boolean NOT NULL
);

CREATE TABLE "Uzytkownik" (
  "id_uzytkownika" int PRIMARY KEY NOT NULL,
  "nazwa_uzytkownika" varchar NOT NULL,
  "imie" varchar,
  "adres_email" varchar,
  "jezyk" varchar
);

CREATE TABLE "UprawnieniaUzytkownika" (
  "id_uzytkownika" int NOT NULL,
  "haslo" varchar NOT NULL,
  "admin" boolean NOT NULL,
  "blokada" boolean NOT NULL
);

CREATE TABLE "PomiaryCzujnika" (
  "id_pomiaru" int PRIMARY KEY NOT NULL,
  "id_czujnika" int NOT NULL,
  "wartosc" float NOT NULL,
  "czas" datetime NOT NULL
);

CREATE TABLE "StanyPrzekaznika" (
  "id_stanu" int PRIMARY KEY NOT NULL,
  "id_przekaznika" int NOT NULL,
  "stan" boolean NOT NULL,
  "czas" datetime NOT NULL
);

CREATE TABLE "UrzadzeniaUzytkownika" (
  "id_urzadzenia" int NOT NULL,
  "id_uzytkownika" int NOT NULL
);

ALTER TABLE "Czujnik" ADD FOREIGN KEY ("id_urzadzenia") REFERENCES "Urzadzenie" ("id_urzadzenia");

ALTER TABLE "Przekaznik" ADD FOREIGN KEY ("id_urzadzenia") REFERENCES "Urzadzenie" ("id_urzadzenia");

ALTER TABLE "UprawnieniaUzytkownika" ADD FOREIGN KEY ("id_uzytkownika") REFERENCES "Uzytkownik" ("id_uzytkownika");

ALTER TABLE "PomiaryCzujnika" ADD FOREIGN KEY ("id_czujnika") REFERENCES "Czujnik" ("id_czujnika");

ALTER TABLE "StanyPrzekaznika" ADD FOREIGN KEY ("id_przekaznika") REFERENCES "Przekaznik" ("id_przekaznika");

ALTER TABLE "UrzadzeniaUzytkownika" ADD FOREIGN KEY ("id_urzadzenia") REFERENCES "Urzadzenie" ("id_urzadzenia");

ALTER TABLE "UrzadzeniaUzytkownika" ADD FOREIGN KEY ("id_uzytkownika") REFERENCES "Uzytkownik" ("id_uzytkownika");
