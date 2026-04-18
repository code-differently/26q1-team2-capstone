package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.MoodEntryRequest;
import com.team2.mentalhealthtracker.dto.MoodEntryResponse;
import com.team2.mentalhealthtracker.model.CheckIn;
import com.team2.mentalhealthtracker.model.SleepQuality;
import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.repository.CheckInRepository;
import com.team2.mentalhealthtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInServiceTest {

    @Mock
    private CheckInRepository checkInRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CheckInService checkInService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCheckIn() {
        Long userId = 1L;

        User user = new User();
        MoodEntryRequest request = new MoodEntryRequest();
        request.setMoodScore(7);
        request.setStressLevel(4);
        request.setSleepQuality("GOOD");
        request.setJournalNotes("Feeling okay today");
        request.setTriggers("school");
        request.setCopingStrategiesUsed("journaling");

        CheckIn savedCheckIn = new CheckIn();
        savedCheckIn.setMoodScore(7);
        savedCheckIn.setStressLevel(4);
        savedCheckIn.setSleepQuality(SleepQuality.GOOD);
        savedCheckIn.setJournalNotes("Feeling okay today");
        savedCheckIn.setTriggers("school");
        savedCheckIn.setCopingStrategiesUsed("journaling");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(checkInRepository.save(any(CheckIn.class))).thenReturn(savedCheckIn);

        MoodEntryResponse response = checkInService.createCheckIn(userId, request);

        assertNotNull(response);
        assertEquals(7, response.getMoodScore());
        assertEquals(4, response.getStressLevel());
        assertEquals("GOOD", response.getSleepQuality());
        assertEquals("Feeling okay today", response.getJournalNotes());
        assertEquals("school", response.getTriggers());
        assertEquals("journaling", response.getCopingStrategiesUsed());

        verify(userRepository, times(1)).findById(userId);
        verify(checkInRepository, times(1)).save(any(CheckIn.class));
    }

    @Test
    void shouldReturnCheckInsByUser() {
        Long userId = 1L;

        User user = new User();

        CheckIn checkIn = new CheckIn();
        checkIn.setMoodScore(8);
        checkIn.setStressLevel(3);
        checkIn.setSleepQuality(SleepQuality.OKAY);
        checkIn.setJournalNotes("Pretty good day");
        checkIn.setTriggers("work");
        checkIn.setCopingStrategiesUsed("walking");
        checkIn.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(checkInRepository.findByUserOrderByCreatedAtAsc(user)).thenReturn(List.of(checkIn));

        List<MoodEntryResponse> result = checkInService.getCheckInsByUser(userId);

        assertEquals(1, result.size());
        assertEquals(8, result.get(0).getMoodScore());
        assertEquals(3, result.get(0).getStressLevel());
        assertEquals("OKAY", result.get(0).getSleepQuality());
        assertEquals("Pretty good day", result.get(0).getJournalNotes());
        assertEquals("work", result.get(0).getTriggers());
        assertEquals("walking", result.get(0).getCopingStrategiesUsed());

        verify(userRepository, times(1)).findById(userId);
        verify(checkInRepository, times(1)).findByUserOrderByCreatedAtAsc(user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundDuringCreateCheckIn() {
        Long userId = 99L;

        MoodEntryRequest request = new MoodEntryRequest();
        request.setMoodScore(5);
        request.setStressLevel(5);
        request.setSleepQuality("GOOD");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                checkInService.createCheckIn(userId, request));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(checkInRepository, never()).save(any(CheckIn.class));
    }

    @Test
    void shouldThrowWhenGettingCheckInsForMissingUser() {
        Long userId = 99L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> checkInService.getCheckInsByUser(userId));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(checkInRepository, never()).findByUserOrderByCreatedAtAsc(any());
    }

    @Test
    void shouldThrowWhenSleepQualityIsBlank() {
        Long userId = 1L;

        User user = new User();
        MoodEntryRequest request = new MoodEntryRequest();
        request.setMoodScore(7);
        request.setStressLevel(4);
        request.setSleepQuality(" ");
        request.setJournalNotes("Feeling off");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> checkInService.createCheckIn(userId, request));

        assertEquals("Sleep quality is required", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(checkInRepository, never()).save(any(CheckIn.class));
    }

    @Test
    void shouldThrowWhenSleepQualityIsInvalid() {
        Long userId = 1L;

        User user = new User();
        MoodEntryRequest request = new MoodEntryRequest();
        request.setMoodScore(7);
        request.setStressLevel(4);
        request.setSleepQuality("amazing");
        request.setJournalNotes("Feeling off");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> checkInService.createCheckIn(userId, request));

        assertEquals("Invalid sleep quality. Use GOOD, OKAY, or POOR", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(checkInRepository, never()).save(any(CheckIn.class));
    }
}