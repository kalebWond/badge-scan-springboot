package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.entities.PlanResponseDTO;
import lombok.Data;


import java.util.ArrayList;
import java.util.Collection;
@Data
public class Plans {

    Collection<PlanResponseDTO> planDTOList = new ArrayList<>();

}
