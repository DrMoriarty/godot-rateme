extends Node

signal finished

var _ln = null

func _ready():
    if Engine.has_singleton("RateMe"):
        _ln = Engine.get_singleton("RateMe")
        print('RateMe plugin inited')
        _ln.connect('finished', self, '_on_rateme_finished')
    else:
        push_warning('RateMe plugin not found!')

func show():
    if _ln != null:
        _ln.showRateMe()

func _on_rateme_finished() -> void:
    emit_signal('finished')
