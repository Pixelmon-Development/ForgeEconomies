package com.envyful.economies.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.Argument;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.economies.api.Bank;
import com.envyful.economies.api.Economy;
import com.envyful.economies.forge.EconomiesForge;
import com.envyful.economies.forge.player.EconomiesAttribute;
import net.minecraft.entity.player.EntityPlayerMP;

@Command(
        value = "balance",
        description = "Get balance for bank account",
        aliases = {
                "ebalance",
                "ebal",
                "bal"
        }
)
public class BalanceCommand {

    @CommandProcessor
    public void onCommand(@Sender EntityPlayerMP player, @Argument Economy economy) {
        EnvyPlayer<EntityPlayerMP> envyPlayer = EconomiesForge.getInstance().getPlayerManager().getPlayer(player);
        EconomiesAttribute playerAttribute = envyPlayer.getAttribute(EconomiesForge.class);

        if (playerAttribute == null) {
            return;
        }

        Bank playerAccount = playerAttribute.getAccount(economy);

        envyPlayer.message(UtilChatColour.translateColourCodes('&', EconomiesForge.getInstance()
                .getLocale().getBalance().replace("%value%",
                        (economy.isPrefix() ? economy.getEconomyIdentifier() : "") + playerAccount.getBalance()
                                + (!economy.isPrefix() ? economy.getEconomyIdentifier() : ""))));
    }
}