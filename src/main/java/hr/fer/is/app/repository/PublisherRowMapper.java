package hr.fer.is.app.repository;

import hr.fer.is.app.domain.Publisher;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link Publisher}, with proper type conversions.
 */
@Service
public class PublisherRowMapper implements BiFunction<Row, String, Publisher> {

    private final ColumnConverter converter;

    public PublisherRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Publisher} stored in the database.
     */
    @Override
    public Publisher apply(Row row, String prefix) {
        Publisher entity = new Publisher();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setOib(converter.fromRow(row, prefix + "_oib", String.class));
        return entity;
    }
}
