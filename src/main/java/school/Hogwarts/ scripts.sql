select  * from  student where age between 10 and 20;

select name from  student;

select  * from  student where name like  '%e%' or name like '%E%';

select * from  student where age < id;

select  * from  student order by age;

select faculty.name, student.name, student.age from student, faculty
where student.faculty_id  = faculty.id  and faculty.id = 3;