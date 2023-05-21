package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.PrintJobMapper;
import hr.fer.is.app.domain.PrintJob;
import hr.fer.is.app.domain.dto.PrintJobDTO;
import hr.fer.is.app.repository.PrintJobRepository;
import hr.fer.is.app.service.CoverTypeService;
import hr.fer.is.app.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrintJobServiceImplTest {

    @InjectMocks
    private PrintJobServiceImpl printJobServiceImpl;

    @Mock
    private PrintJobRepository printJobRepository;
    @Mock
    private PrintJobMapper printJobMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        PrintJob printJob = new PrintJob();
        PrintJobDTO printJobDTO = new PrintJobDTO();
        when(printJobRepository.findAll()).thenReturn(Arrays.asList(printJob));
        when(printJobMapper.toDTO(printJob)).thenReturn(printJobDTO);
        List<PrintJobDTO> result = printJobServiceImpl.findAll();
        assertEquals(1, result.size());
        verify(printJobRepository).findAll();
    }

    @Test
    void findById_found() {
        PrintJob printJob = new PrintJob();
        PrintJobDTO printJobDTO = new PrintJobDTO();
        when(printJobRepository.findById(1L)).thenReturn(Optional.of(printJob));
        when(printJobMapper.toDTO(printJob)).thenReturn(printJobDTO);
        PrintJobDTO result = printJobServiceImpl.findById(1L);
        assertNotNull(result);
    }

    @Test
    void findById_notFound() {
        when(printJobRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> printJobServiceImpl.findById(1L));
    }

    @Test
    void save() {
        PrintJobDTO printJobDTO = new PrintJobDTO();
        PrintJob printJob = new PrintJob();
        when(printJobMapper.toEntity(printJobDTO)).thenReturn(printJob);
        when(printJobRepository.save(printJob)).thenReturn(printJob);
        when(printJobMapper.toDTO(printJob)).thenReturn(printJobDTO);
        PrintJobDTO result = printJobServiceImpl.save(printJobDTO);
        assertNotNull(result);
        verify(printJobRepository).save(printJob);
    }

    @Test
    void update_found() {
        PrintJobDTO printJobDTO = new PrintJobDTO();
        PrintJob printJob = new PrintJob();
        when(printJobRepository.findById(1L)).thenReturn(Optional.of(printJob));
        when(printJobMapper.toEntity(printJobDTO)).thenReturn(printJob);
        when(printJobRepository.save(printJob)).thenReturn(printJob);
        when(printJobMapper.toDTO(printJob)).thenReturn(printJobDTO);
        PrintJobDTO result = printJobServiceImpl.update(1L, printJobDTO);
        assertNotNull(result);
        verify(printJobRepository).save(printJob);
    }

    @Test
    void update_notFound() {
        PrintJobDTO printJobDTO = new PrintJobDTO();
        when(printJobRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> printJobServiceImpl.update(1L, printJobDTO));
    }

    @Test
    void delete_found() {
        PrintJob printJob = new PrintJob();
        when(printJobRepository.findById(1L)).thenReturn(Optional.of(printJob));
        printJobServiceImpl.delete(1L);
        verify(printJobRepository).delete(printJob);
    }

    @Test
    void delete_notFound() {
        when(printJobRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> printJobServiceImpl.delete(1L));
    }

    @Test
    void findAllByPublisherId() {
        PrintJob printJob = new PrintJob();
        PrintJobDTO printJobDTO = new PrintJobDTO();
        when(printJobRepository.findAllByPublisherId(1L)).thenReturn(List.of(printJob));
        when(printJobMapper.toDTO(printJob)).thenReturn(printJobDTO);
        List<PrintJobDTO> result = printJobServiceImpl.findAllByPublisherId(1L);
        assertEquals(1, result.size());
        verify(printJobRepository).findAllByPublisherId(1L);
    }

}
