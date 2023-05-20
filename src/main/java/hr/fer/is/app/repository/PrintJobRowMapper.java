package hr.fer.is.app.repository;

import hr.fer.is.app.domain.PrintJob;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link PrintJob}, with proper type conversions.
 */
@Service
public class PrintJobRowMapper implements BiFunction<Row, String, PrintJob> {

    private final ColumnConverter converter;

    public PrintJobRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PrintJob} stored in the database.
     */
    @Override
    public PrintJob apply(Row row, String prefix) {
        PrintJob entity = new PrintJob();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setBookTitle(converter.fromRow(row, prefix + "_book_title", String.class));
        entity.setWriterName(converter.fromRow(row, prefix + "_writer_name", String.class));
        entity.setPageNumber(converter.fromRow(row, prefix + "_page_number", Integer.class));
        entity.setEditionNumber(converter.fromRow(row, prefix + "_edition_number", Integer.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setStartDate(converter.fromRow(row, prefix + "_start_date", LocalDate.class));
        entity.setDeadline(converter.fromRow(row, prefix + "_deadline", LocalDate.class));
        entity.setArchive(converter.fromRow(row, prefix + "_archive", Boolean.class));
        entity.setPublisherId(converter.fromRow(row, prefix + "_publisher_id", Long.class));
        entity.setCoverTypeId(converter.fromRow(row, prefix + "_cover_type_id", Long.class));
        return entity;
    }
}
