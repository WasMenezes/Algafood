package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Kitchen;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "kitchens")
@Data
public class KitchensXmlWrapper {

    @NonNull
    List<Kitchen> kitchens;
}
