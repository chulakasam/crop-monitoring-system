package com.example.Proposed.Crop.monitoring.system.customStatusCodes;

import com.example.Proposed.Crop.monitoring.system.Dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserErrorStatus implements UserStatus, StaffStatus, VehicleStatus, FieldStatus, EquipmentStatus,CropStatus,MonitoringLogStatus {
    private int statusCode;
    private String statusMessage;
}
