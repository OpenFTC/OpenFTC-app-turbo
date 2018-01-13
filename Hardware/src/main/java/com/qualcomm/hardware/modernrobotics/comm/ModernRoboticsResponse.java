/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.qualcomm.hardware.modernrobotics.comm;

/**
 * {@link ModernRoboticsResponse} helps parse datagram responses received from Modern
 * Robotics controllers.
 */
@SuppressWarnings("WeakerAccess")
public class ModernRoboticsResponse extends ModernRoboticsDatagram {
    //----------------------------------------------------------------------------------------------
    // State
    //----------------------------------------------------------------------------------------------

    public static final byte[] syncBytes = new byte[]{(byte) 0x33, (byte) 0xCC};

    protected final AllocationContext<ModernRoboticsResponse> allocationContext;

    //----------------------------------------------------------------------------------------------
    // Construction
    //----------------------------------------------------------------------------------------------

    private ModernRoboticsResponse(AllocationContext<ModernRoboticsResponse> allocationContext, int cbPayloadAlloc) {
        super(cbPayloadAlloc);
        this.allocationContext = allocationContext;
    }

    public static ModernRoboticsResponse newInstance(AllocationContext<ModernRoboticsResponse> allocationContext, int cbPayloadAlloc) {
        ModernRoboticsResponse instance = allocationContext.tryAlloc(cbPayloadAlloc);
        if (instance == null) {
            instance = new ModernRoboticsResponse(allocationContext, cbPayloadAlloc);
        }
        instance.initialize(syncBytes[0], syncBytes[1]);
        return instance;
    }

    public void close() {
        allocationContext.tryCache0(this);
    }

    //----------------------------------------------------------------------------------------------
    // Accessing
    //----------------------------------------------------------------------------------------------

    public boolean syncBytesValid() {
        return data[IB_SYNC_0] == syncBytes[0] && data[IB_SYNC_1] == syncBytes[1];
    }
}
