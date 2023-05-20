package hr.fer.is.app.repository;

import hr.fer.is.app.domain.UserRole;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link UserRole}, with proper type conversions.
 */
@Service
public class UserRoleRowMapper implements BiFunction<Row, String, UserRole> {

    private final ColumnConverter converter;

    public UserRoleRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link UserRole} stored in the database.
     */
    @Override
    public UserRole apply(Row row, String prefix) {
        UserRole entity = new UserRole();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
