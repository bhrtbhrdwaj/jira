package com.jira.constant;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class EntityStatus extends ImmutableType<EntityStatus.Type> {

    public enum Type {
        INACTIVE(0),
        ACTIVE(1);
        private static final Map<Integer, Type> map;
        static {
            map = Arrays.stream(Type.values()).collect(Collectors.toMap(Type::getValue, Function.identity()));
        }
        private final int value;
        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Type getDefault() {
            return null;
        }

        public static Type getType(int value) {
            return map.getOrDefault(value, getDefault());
        }

        public static Type getType(boolean isEnable) {
            return isEnable? Type.ACTIVE : Type.INACTIVE;
        }

    }

    public EntityStatus() {
        super(Type.class);
    }

    @Override
    protected Type get(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        int value = rs.getInt(names[0]);
        return Type.getType(value);
    }

    @Override
    protected void set(PreparedStatement st, Type type, int index, SharedSessionContractImplementor session) throws SQLException {
        if (type == null) {
            st.setNull(index, Types.INTEGER);
        } else {
            st.setInt(index, type.value);
        }
    }

    /**
     * Return the SQL type codes for the columns mapped by this type. The
     * codes are defined on <tt>java.sql.Types</tt>.
     *
     * @return int[] the type codes
     * @see Types
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.INTEGER};
    }
}