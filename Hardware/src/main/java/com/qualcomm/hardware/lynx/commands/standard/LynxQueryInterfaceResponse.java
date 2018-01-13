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
package com.qualcomm.hardware.lynx.commands.standard;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.lynx.commands.LynxDatagram;
import com.qualcomm.hardware.lynx.commands.LynxInterface;
import com.qualcomm.hardware.lynx.commands.LynxResponse;
import com.qualcomm.robotcore.util.TypeConversion;
import com.qualcomm.robotcore.util.Util;

import java.nio.ByteBuffer;

/**
 * Created by bob on 2016-03-06.
 */
public class LynxQueryInterfaceResponse extends LynxStandardResponse {
    //----------------------------------------------------------------------------------------------
    // State
    //----------------------------------------------------------------------------------------------

    short commandNumberFirst = LynxInterface.ERRONEOUS_COMMAND_NUMBER;
    short numberOfCommands = 0;

    //----------------------------------------------------------------------------------------------
    // Construction
    //----------------------------------------------------------------------------------------------

    public LynxQueryInterfaceResponse(LynxModule module) {
        super(module);
    }

    //----------------------------------------------------------------------------------------------
    // Accessors
    //----------------------------------------------------------------------------------------------

    public static int getStandardCommandNumber() {
        return LynxQueryInterfaceCommand.getStandardCommandNumber() | LynxResponse.RESPONSE_BIT;
    }

    public int getCommandNumberFirst() {
        return TypeConversion.unsignedShortToInt(this.commandNumberFirst);
    }

    //----------------------------------------------------------------------------------------------
    // Operations
    //----------------------------------------------------------------------------------------------

    public int getNumberOfCommands() {
        return TypeConversion.unsignedShortToInt(this.numberOfCommands);
    }

    @Override
    public int getCommandNumber() {
        return getStandardCommandNumber();
    }

    @Override
    public byte[] toPayloadByteArray() {
        return Util.concatenateByteArrays(
                TypeConversion.shortToByteArray(this.commandNumberFirst, LynxDatagram.LYNX_ENDIAN),
                TypeConversion.shortToByteArray(this.numberOfCommands, LynxDatagram.LYNX_ENDIAN));
    }

    @Override
    public void fromPayloadByteArray(byte[] rgb) {
        ByteBuffer buffer = ByteBuffer.wrap(rgb);
        buffer.order(LynxDatagram.LYNX_ENDIAN);
        this.commandNumberFirst = buffer.getShort();
        this.numberOfCommands = buffer.getShort();
    }

    //----------------------------------------------------------------------------------------------
    // Utility
    //----------------------------------------------------------------------------------------------

}
