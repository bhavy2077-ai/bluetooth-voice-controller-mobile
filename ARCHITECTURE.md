# Architecture Overview

## System Design

```
LAPTOP (Client)           Bluetooth RFCOMM         PHONE (Server)
─────────────────────────────────────────────────────────────────
  PyQt5 GUI       ────────  Encrypted JSON  ────→  Android App
     ↓                                                ↓
  Microphone                                    Bluetooth Server
     ↓                                                ↓
  Speech API                                    Command Parser
     ↓                                                ↓
  Encryption                                   Permission Check
     ↓                                                ↓
  Bluetooth Send ←─────────  Response  ──────  Command Executor
```

## Mobile Components

1. **MainActivity** - UI & connection handler
2. **CommandExecutor** - Intent-based app launching
3. **SecurityUtils** - AES encryption/decryption
4. **BluetoothService** - Background socket handling

## Data Flow

1. Voice input captured on laptop
2. Converted to text via Google Speech API
3. Encrypted with AES-256
4. Sent via Bluetooth RFCOMM
5. Phone receives encrypted command
6. Decrypted using shared password
7. Parsed and validated
8. Permissions checked
9. Intent executed
10. Response sent back
11. UI updated

## Security Stack

- Transport: Bluetooth encryption
- Data: AES-256 encryption
- Authentication: Shared password
- Permissions: Android security model
