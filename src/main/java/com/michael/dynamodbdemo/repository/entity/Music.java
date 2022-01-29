package com.michael.dynamodbdemo.repository.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Data
@DynamoDbBean()
public class Music {

    private String artist;
    private String songTitle;
    private String albumTitle;
    private String genre;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("Artist")
    public String getArtist() {
        return artist;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SongTitle")
    public String getSongTitle() {
        return songTitle;
    }

    @DynamoDbAttribute("AlbumTitle")
    public String getAlbumTitle() {
        return albumTitle;
    }

    @DynamoDbAttribute("Genre")
    public String getGenre() {
        return genre;
    }
}