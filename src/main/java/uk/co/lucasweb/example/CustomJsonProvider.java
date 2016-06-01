package uk.co.lucasweb.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author Richard Lucas
 */
@Provider
@Consumes({"application/*+json"})
@Produces({"application/*+json"})
public class CustomJsonProvider extends JacksonJaxbJsonProvider {

    public CustomJsonProvider() {
        super();
        super.setAnnotationsToUse(DEFAULT_ANNOTATIONS);
        super.setMapper(getObjectMapper());
    }

    @Override
    public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException {
        // Workaround for a null-pointer issue that occurs when serializing entities in an ExceptionMapper
        Annotation[] writeToAnnotationArray = annotations;
        if (writeToAnnotationArray == null) {
            writeToAnnotationArray = new Annotation[0];
        }
        super.writeTo(value, type, genericType, writeToAnnotationArray, mediaType, httpHeaders, entityStream);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper;
    }
}
