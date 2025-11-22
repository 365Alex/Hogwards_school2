alter TABLE Student
ADD CONSTRAINT student_age CHECK (age >= 16);

alter TABLE Student
ADD CONSTRAINT uk_student_name UNIQUE (name);

alter TABLE Student
ALTER COLUMN name SET NOT NULL;

alter TABLE Student
ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE Faculty
ADD CONSTRAINT uk_faculty_name_color UNIQUE (name, color);