# API Documentation

## Bluetooth Communication Protocol

### Message Format
All messages are JSON-encoded and encrypted:
```json
{
  "cmd": "<encrypted_command>",
  "timestamp": "<ISO_8601_timestamp>",
  "version": "1.0"
}
```

### Supported Commands

#### App Commands
- `open youtube` → Opens YouTube app
- `call <number>` → Initiates phone call
- `open home screen` → Returns to home
- `open messages` → SMS app
- `open camera` → Camera app
- `open settings` → Settings

#### System Commands
- `toggle wifi` → WiFi control
- `toggle bluetooth` → Bluetooth control
- `toggle flashlight` → Flashlight
- `volume up/down` → Volume control
- `brightness up/down` → Screen brightness
- `take screenshot` → Screenshot

### Response Format
```json
{
  "response": "<encrypted_response>",
  "status": "success|error",
  "timestamp": "<ISO_8601_timestamp>"
}
```

## Security

- **Algorithm**: AES-256-CBC
- **Key Derivation**: SHA-256(password)
- **Encoding**: Base64

## Error Codes

| Code | Meaning |
|------|----------|
| 200 | Success |
| 400 | Invalid command |
| 401 | Auth failed |
| 403 | Permission denied |
| 500 | Server error |
