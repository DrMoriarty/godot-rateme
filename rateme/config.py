def can_build(env, platform):
	if platform == "android":
		return True
	elif platform == "iphone":
		return True
	return False

def configure(env):
	if (env['platform'] == 'android'):
		env.android_add_java_dir("android/src/")
		env.disable_module()
	if (env['platform'] == 'iphone'):
		pass

