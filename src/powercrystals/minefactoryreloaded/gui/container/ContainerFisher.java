package powercrystals.minefactoryreloaded.gui.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

import powercrystals.minefactoryreloaded.tile.base.TileEntityFactoryPowered;
import powercrystals.minefactoryreloaded.tile.machine.TileEntityFisher;

public class ContainerFisher extends ContainerFactoryPowered {

	public ContainerFisher(TileEntityFactoryPowered te, InventoryPlayer inv)
	{
		super(te, inv);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++)
		{
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 100, ((TileEntityFactoryPowered)_te).getWorkMax() & 65535);
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 101, ((TileEntityFactoryPowered)_te).getWorkMax() >>> 16);
		}
	}

	@Override
	public void updateProgressBar(int var, int value)
	{
		super.updateProgressBar(var, value);

		if(var == 100) workTemp = (value & 65535);
		else if(var == 101) ((TileEntityFisher)_te).setWorkMax(((value & 65535) << 16) | workTemp);
	}

}
