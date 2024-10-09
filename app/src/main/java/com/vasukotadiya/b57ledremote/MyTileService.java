package com.vasukotadiya.b57ledremote;

import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class MyTileService extends TileService {

        IRTransmitter irTransmitter;
    @Override
    public void onTileAdded() {
        // Called when the tile is added to Quick Settings
        Tile tile = getQsTile();
        tile.setSubtitle("LED ON");
        tile.setLabel("LED ON");
        irTransmitter=new IRTransmitter(this);
        tile.setIcon(Icon.createWithResource(this,R.drawable.ic_baseline_lightbulb_24));
        tile.setState(Tile.STATE_INACTIVE); // Initial state
        tile.updateTile();
    }

    @Override
    public void onStartListening() {
        // Called when the tile becomes visible
        Tile tile = getQsTile();
        tile.updateTile();
    }

    @Override
    public void onStopListening() {
        // Called when the tile is no longer visible
    }

    @Override
    public void onTileRemoved() {
        // Called when the tile is removed from Quick Settings
    }

    @Override
    public void onClick() {
        // Handle tile click event
        Tile tile = getQsTile();

        if (tile.getState() == Tile.STATE_INACTIVE) {
            // Turn on lights (or execute any specific function)
            // Example: Send IR signal to turn lights ON
            tile.setLabel("LED ON");
            irTransmitter.transmitProntoHex(3);
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            // Turn off lights (or execute another function)
            // Example: Send IR signal to turn lights OFF
            tile.setLabel("LED OFF");
            irTransmitter.transmitProntoHex(2);
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile(); // Update the tile with the new state
    }
}

