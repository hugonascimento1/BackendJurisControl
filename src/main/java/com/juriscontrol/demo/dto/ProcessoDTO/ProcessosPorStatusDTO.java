package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessosPorStatusDTO {
    private String status;
    private Long quantidade;
}
