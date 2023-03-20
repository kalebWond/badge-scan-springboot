package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.PlanDTO;
import lombok.Data;


import java.util.ArrayList;
import java.util.Collection;
@Data
public class Plans {

    Collection<PlanDTO> planDTOList = new ArrayList<>();

}
