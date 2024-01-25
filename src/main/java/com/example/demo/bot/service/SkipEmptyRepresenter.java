package com.example.demo.bot.service;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.List;
import java.util.Map;
import java.util.Objects;

class SkipEmptyRepresenter extends Representer {
    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
        if (isInvalidType(property, propertyValue)) {
            return null;
        }
        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isInvalidType(Property property, Object propertyValue) {
        if (propertyValue == null) {
            return true;
        }
        if (propertyValue instanceof List && ((List) propertyValue).isEmpty()) {
            return true;
        }
        if (propertyValue instanceof Map && ((Map) propertyValue).isEmpty()) {
            return true;
        }
        if (propertyValue instanceof Boolean && !((Boolean) propertyValue) && !Objects.equals(property.getName(), "failed")) {
            return true;
        }
        return false;
    }
}
