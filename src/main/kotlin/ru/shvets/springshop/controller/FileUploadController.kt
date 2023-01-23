package ru.shvets.springshop.controller

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.File
import java.nio.file.Paths
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  12.01.2023 20:56
 */

@Controller
@RequestMapping("/upload")
class FileUploadController {
    private val logger = KotlinLogging.logger {}

    @Value("\${upload.path}")
    lateinit var uploadPath: String

    @GetMapping
    fun listUploadedFiles(model: Model): String {
        return "/products/uploadForm"
    }

    @PostMapping
    fun handleFileUpload(
        @RequestParam("file") file: MultipartFile,
        redirectAttributes: RedirectAttributes
    ): String {

        if (file.isEmpty) {
            redirectAttributes.addFlashAttribute("message",
                "Please select a file to upload");
            return "redirect:/upload"
        }

        val uploadDir = File(uploadPath)

        if (!uploadDir.exists()) {
            logger.error("Target folder is not exists")
            uploadDir.mkdir()
        }

        val uuidFile = UUID.randomUUID().toString().replace("-", "")
        val resultFileName = uuidFile + "." + file.originalFilename
        val filepath: String = Paths.get(uploadDir.toString(), resultFileName).toString()

//        file.transferTo(Path(filepath))
        file.transferTo(File("$uploadPath/$resultFileName"))
        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.originalFilename + "!")

        redirectAttributes.addFlashAttribute("filename", resultFileName)
        return "redirect:/upload"
    }
}