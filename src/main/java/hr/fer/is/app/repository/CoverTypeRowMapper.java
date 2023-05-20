package hr.fer.is.app.repository;

import hr.fer.is.app.domain.CoverType;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link CoverType}, with proper type conversions.
 */
@Service
public class CoverTypeRowMapper implements BiFunction<Row, String, CoverType> {

    private final ColumnConverter converter;

    public CoverTypeRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CoverType} stored in the database.
     */
    @Override
    public CoverType apply(Row row, String prefix) {
        CoverType entity = new CoverType();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
