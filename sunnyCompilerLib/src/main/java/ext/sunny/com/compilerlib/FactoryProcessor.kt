package ext.sunny.com.compilerlib

import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import ext.sunny.com.annotationlib.ShapeFactory
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**@Annotation <p>注解读取处理器</p>
 * @Auth  Sunny
 * AutoService会自动生成在src/resources/META-INF.services/javax.annotation.processing.Processor
 * 但我添加后没有生成 ，看网友有说(https://www.jianshu.com/p/96bf387f79ef)，可能是gradle版本的问题，
 * 得降低版本，但我没试了，
 * 我是自己手动生成 的
 * @date 2020/3/6
 * @Version V1.0.0
 */
@AutoService(Process::class)//这个是Google库，便于自动生成META-INF.services下面的文件
//用于指定此自定义注解器将主要读取哪些注解，一定得写，否则无法生成代码
@SupportedAnnotationTypes("ext.sunny.com.annotationlib.ShapeFactory")

class FactoryProcessor : AbstractProcessor() {

    var filer: Filer? = null//用于生成创建文件
    var elementUtil: Elements? = null//元数据信息整合类
    var messager: Messager? = null//用于打印消息
    /**
     * @param p0:为遍历得到的所有的类
     * @param p1:为指定类中所有的注解元数据信息
     */
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        println("FactoryProcessor.process")
        p0?.let {
            p0.forEach { typeElement ->
                run {
                    p1?.getElementsAnnotatedWith(typeElement)?.forEach {
                        //获取包名
                        val packagename = elementUtil?.getPackageOf(it)?.qualifiedName.toString()
                        messager?.printMessage(Diagnostic.Kind.NOTE, "packageName:$packagename")
                        //获取类名
                        val classname = it.simpleName.toString()
                        messager?.printMessage(Diagnostic.Kind.NOTE, "className:$classname")

                        //读取类信息
                        var typeElement: TypeElement = it as TypeElement
                        autoGenCode(typeElement, filer, packagename, classname)

                    }
                }

            }
        }



        return true//返回true，代表由当前处理器读取处理，否则由其他的处理
    }

    /**
     * 自动生成代码片断
     */
    private fun autoGenCode(
        typeElement: TypeElement,
        filer: Filer?,
        packagename: String,
        classname: String
    ) {

        println("className:$classname")
//        var superClassName = elementUtil?.getTypeElement(classname)
        var factoryClassName = "${classname}Factory"

        //用MethodSpec来生成方法
        var methodBuild: MethodSpec.Builder = MethodSpec.methodBuilder("create")
            .addModifiers(Modifier.PUBLIC)
            .addParameter(String::class.java, "id")
//            .returns(TypeName.get(superClassName?.asType()))//添加返回值

        //添加方法体中的控制逻辑
//        methodBuild.beginControlFlow("if ( id == null)")
//            .addStatement("throw new IllegalArgumentExceptions(id is Null)")
//            .endControlFlow()


        var typeSpec: TypeSpec = TypeSpec.classBuilder(factoryClassName)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(methodBuild.build())
            .build()

        //最后利用Filer在gen中自动生成
        JavaFile.builder(packagename, typeSpec).build().writeTo(filer)

    }

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
        filer = p0!!.filer
        elementUtil = p0!!.elementUtils
        messager = p0!!.messager
    }

    /**
     * 获取JDK的版本
     */
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_8
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return super.getSupportedAnnotationTypes()
    }
}