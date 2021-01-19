package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        var timeEntryFound = timeEntryRepository.find(timeEntryId);
        return timeEntryFound == null ?
                notFound().build() :
                ok(timeEntryFound);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ok(timeEntryRepository.list());
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        var timeEntryUpdated = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);

        return timeEntryUpdated == null ?
                notFound().build() :
                ok(timeEntryUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        return noContent().build();
    }
}
