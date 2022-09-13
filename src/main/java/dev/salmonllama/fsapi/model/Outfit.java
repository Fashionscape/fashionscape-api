package dev.salmonllama.fsapi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

import java.io.Serializable;
import java.util.Date;

public class Outfit implements Serializable {
    public String id;
    public String link;
    public String submitter;
    public String tag;
    public String meta;
    public String discordName;
    public int displayCount;
    public String deleteHash;

    @JsonSerialize(using = DateSerializer.class)
    public Date created;
    @JsonSerialize(using = DateSerializer.class)
    public Date updated;

    public Outfit() {}
}
