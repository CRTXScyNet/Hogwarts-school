package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService service) {
        this.facultyService = service;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {

        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("{id:\\d+}/students")
    public ResponseEntity<Collection<Student>> getStudents(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.getStudents(id));
    }

    @GetMapping("/color")
    public Collection<Faculty> getFacultyByColor(@RequestParam("color") String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/all")
    public Collection<Faculty> getAllFaculty() {
        return facultyService.getAllFaculties();
    }

    @GetMapping()
    public ResponseEntity<Faculty> findByNameOrColor(@RequestParam String part) {
        Faculty faculty = facultyService.findByNameOrColor(part);
        if (faculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/longest-name")
    public ResponseEntity<String> getLongestName() {
        String s = facultyService.getLongestFacultyName();
        return ResponseEntity.ok(s);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(faculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(faculty);
    }


}
