import os
import gzip
import re


def count_death():
    os.chdir('/home/riley/workspace/Practice/python_practice/\
minecraft_data_mining/logs')
    log = {}
    for single_log in os.listdir():
        if(single_log[-3:] == '.gz'):
            with gzip.open(single_log, 'rb') as file:
                for line in file:
                    message = process_message(line)
                    if(message is not None):
                        if (message[0] in log):
                            #print(log[message[0]])
                    # this is rather ugly. figure out if there's a better way
                            log[message[0]].append(message[1][0])
                        else:
                            log[message[0]] = message[1]
    tally_player_events(log)
                #print(log)


def process_message(byte_line):
    '''Parses a line from the log and returns a tuple consisting of the player
    involved and a one item list containing a tuple of the event and time. It
    is a list because the dictionary that it is then added to is a
    {player: [(event,time)]} dictionary
    '''
    time, message = byte_line.decode('ascii').split(' ', 1)
    #print(message)
    message = re.sub(r'\[.*\]: ', '', message)
    if(is_player_message(message)):
            #print(message)
            player, message = message.split(' ', 1)
            if(re.search(r'[/(\d{1-3}\.){4}\:\d+]', player) is None):
                if(not (message.startswith('lost connection') or
                   message.startswith('has just earned'))):
                    return (player, [(message, time)])
    return None


def is_player_message(message):
    '''This really needs some way of knowing the names of players on the server
    This could possibly be done by looking for 'x joined the game' messages
    beforehand. Currently exploring other options
    '''
    #patterns = [r'Can\'t keep up!', r'Starting minecraft',
    #            r'Loading properties', r'Default game', r'Generating keypair',
    #            r'Starting Minecraft', r'Using epoll channel', r'\*\*\*\*',
    #           r'The server', r'While this', r'To change', r'Preparing level',
    #            r'Preparing start', r'Preparing spawn', r'Unknown command',
    #            r'There are', r'^\S*$', r'Done \(', r'sun\.', r'java\.',
    #            r'pr\.']
    #for pattern in patterns:
    #    if(re.search(pattern, message) is not None):
    #        return False
    #if(message[0] not in {'/', '-', '[', '<'}):
    #    return True
    if(re.search(r'^\S*$', message) is not None):
        return False
    if(re.search(r'^(dulynoted|PlusTwoMace)', message) is not None):
        return True
    return False


def tally_player_events(log):
    for player in log:
        print(player)
        events = {}
        for x in log[player]:
            if(x[0] in events):
                events[x[0]] += 1
            else:
                events[x[0]] = 1
        print(events)


count_death()
