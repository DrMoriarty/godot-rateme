extends Node

signal finished

var _ln = null

func _ready():
    if Engine.has_singleton("RateMe"):
        _ln = Engine.get_singleton("RateMe")
        print('RateMe plugin inited')
        _ln.connect('finished', self, '_on_rateme_finished')
    elif OS.get_name() == 'iOS':
        _ln = load("res://addons/rateme-ios/rateme.gdns").new()
        print('RateMe plugin inited')
    else:
        push_warning('RateMe plugin not found!')

func show():
    if _ln != null:
        _ln.showRateMe()

func _on_rateme_finished() -> void:
    emit_signal('finished')
