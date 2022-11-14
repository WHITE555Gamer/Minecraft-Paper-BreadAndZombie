package com.github.white555gamer.breadandzombie;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.breadandzombie.BreadAndZombie.countBreadAndZombie;
import static com.github.white555gamer.breadandzombie.BreadAndZombie.getInstance;

public class BreadAndZombieCommand implements CommandExecutor, TabCompleter {

    private static final String TYPE_ON = "on", TYPE_OFF = "off", TYPE_SETNUM = "setNum";
    private static final String BASE_MESSAGE = "が設定されました", INVALID_INPUT_MESSAGE = "不正な入力形式です", ALREADY_RUNNING = "すでにオンです", ALREADY_UNRUNNING = "すでにオフです";
    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of(TYPE_ON, TYPE_OFF, TYPE_SETNUM);

    private ItemDeliverTask itemDeliverTask;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        switch (args.length) {
            case 0:
                return true;
            case 1:
                switch (args[0]) {
                    case TYPE_ON:
                        if (BreadAndZombie.isActive) {
                            sender.sendMessage(ALREADY_RUNNING);
                            return true;
                        }
                        BreadAndZombie.isActive = true;
                        sender.sendMessage(TYPE_ON + BASE_MESSAGE);
                        itemDeliverTask = new ItemDeliverTask(getInstance());
                        break;
                    case TYPE_OFF:
                        if (!BreadAndZombie.isActive) {
                            sender.sendMessage(ALREADY_UNRUNNING);
                            return true;
                        }
                        BreadAndZombie.isActive = false;
                        sender.sendMessage(TYPE_OFF + BASE_MESSAGE);
                        itemDeliverTask.cancel();
                        break;
                }
                return true;
            case 2:
                if (args[0].equals(TYPE_SETNUM)) {
                    Integer ParseNumber = 0;
                    try {
                        ParseNumber = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(INVALID_INPUT_MESSAGE);
                        return true;
                    }
                    if (ParseNumber > 0) {
                        countBreadAndZombie = ParseNumber;
                        sender.sendMessage(countBreadAndZombie + BASE_MESSAGE);
                        return true;
                    }
                }
        }
        sender.sendMessage(INVALID_INPUT_MESSAGE);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].length() == 0) {
                return COMMAND_SUGGESTIONS;
            } else {
                return COMMAND_SUGGESTIONS.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
            }
        } else {
            return ImmutableList.of();
        }
    }
}
