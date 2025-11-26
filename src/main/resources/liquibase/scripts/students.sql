-- liquibase formatted sql

-- changeset irina:create_student_name_index
CREATE INDEX idx_students_name ON students(name);

-- changeset irina:create_faculty_name_color_index
CREATE INDEX idx_faculties_name_color ON faculties(name, color);

