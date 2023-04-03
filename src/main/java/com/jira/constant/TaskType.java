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

public class TaskType extends ImmutableType<TaskType.Type> {
    public static final String STORY = "0";
    public static final String FEATURE = "1";
    public static final String BUG = "2";

    public TaskType() {
        super(TaskType.Type.class);
    }

    @Override
    protected TaskType.Type get(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        int value = rs.getInt(names[0]);
        return TaskType.Type.getType(value);
    }

    @Override
    protected void set(PreparedStatement st, TaskType.Type type, int index, SharedSessionContractImplementor session) throws SQLException {
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

    public enum Type {
        STORY(0),
        FEATURE(1),
        BUG(2);
        private static final Map<Integer, Type> map;

        static {
            map = Arrays.stream(TaskType.Type.values()).collect(Collectors.toMap(TaskType.Type::getValue, Function.identity()));
        }

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public static TaskType.Type getDefault() {
            return null;
        }

        public static TaskType.Type getType(int value) {
            return map.getOrDefault(value, getDefault());
        }

        public int getValue() {
            return value;
        }

    }
}