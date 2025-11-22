SELECT s.name AS student_name, s.age, f.name AS faculty_name
FROM Student s
JOIN Faculty f ON s.faculty_id = f.id;

SELECT s.name AS student_name, s.age, f.name AS faculty_name
FROM Student s
JOIN Faculty f ON s.faculty_id = f.id
WHERE s.avatar_id IS NOT NULL;