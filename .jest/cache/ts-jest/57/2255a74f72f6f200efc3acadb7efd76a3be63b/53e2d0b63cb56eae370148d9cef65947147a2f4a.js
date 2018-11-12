"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T13:29:38.506 */
var MainTypes;
(function (MainTypes) {
    var AccessibilityLiveRegionEnum;
    (function (AccessibilityLiveRegionEnum) {
        AccessibilityLiveRegionEnum["assertive"] = "assertive";
        AccessibilityLiveRegionEnum["none"] = "none";
        AccessibilityLiveRegionEnum["polite"] = "polite";
    })(AccessibilityLiveRegionEnum = MainTypes.AccessibilityLiveRegionEnum || (MainTypes.AccessibilityLiveRegionEnum = {}));
    var AlignmentModeEnum;
    (function (AlignmentModeEnum) {
        AlignmentModeEnum["alignBounds"] = "alignBounds";
        AlignmentModeEnum["alignMargins"] = "alignMargins";
    })(AlignmentModeEnum = MainTypes.AlignmentModeEnum || (MainTypes.AlignmentModeEnum = {}));
    var AutoLinkFlagsEnum;
    (function (AutoLinkFlagsEnum) {
        AutoLinkFlagsEnum["all"] = "all";
        AutoLinkFlagsEnum["email"] = "email";
        AutoLinkFlagsEnum["map"] = "map";
        AutoLinkFlagsEnum["none"] = "none";
        AutoLinkFlagsEnum["phone"] = "phone";
        AutoLinkFlagsEnum["web"] = "web";
    })(AutoLinkFlagsEnum = MainTypes.AutoLinkFlagsEnum || (MainTypes.AutoLinkFlagsEnum = {}));
    var AutoSizeTextTypeEnum;
    (function (AutoSizeTextTypeEnum) {
        AutoSizeTextTypeEnum["none"] = "none";
        AutoSizeTextTypeEnum["uniform"] = "uniform";
    })(AutoSizeTextTypeEnum = MainTypes.AutoSizeTextTypeEnum || (MainTypes.AutoSizeTextTypeEnum = {}));
    var BackgroundTintModeEnum;
    (function (BackgroundTintModeEnum) {
        BackgroundTintModeEnum["add"] = "add";
        BackgroundTintModeEnum["multiply"] = "multiply";
        BackgroundTintModeEnum["screen"] = "screen";
        BackgroundTintModeEnum["src_atop"] = "src_atop";
        BackgroundTintModeEnum["src_in"] = "src_in";
        BackgroundTintModeEnum["src_over"] = "src_over";
    })(BackgroundTintModeEnum = MainTypes.BackgroundTintModeEnum || (MainTypes.BackgroundTintModeEnum = {}));
    var BreakStrategyEnum;
    (function (BreakStrategyEnum) {
        BreakStrategyEnum["balanced"] = "balanced";
        BreakStrategyEnum["high_quality"] = "high_quality";
        BreakStrategyEnum["simple"] = "simple";
    })(BreakStrategyEnum = MainTypes.BreakStrategyEnum || (MainTypes.BreakStrategyEnum = {}));
    var ButtonTintModeEnum;
    (function (ButtonTintModeEnum) {
        ButtonTintModeEnum["add"] = "add";
        ButtonTintModeEnum["multiply"] = "multiply";
        ButtonTintModeEnum["screen"] = "screen";
        ButtonTintModeEnum["src_atop"] = "src_atop";
        ButtonTintModeEnum["src_in"] = "src_in";
        ButtonTintModeEnum["src_over"] = "src_over";
    })(ButtonTintModeEnum = MainTypes.ButtonTintModeEnum || (MainTypes.ButtonTintModeEnum = {}));
    var CheckMarkTintModeEnum;
    (function (CheckMarkTintModeEnum) {
        CheckMarkTintModeEnum["add"] = "add";
        CheckMarkTintModeEnum["multiply"] = "multiply";
        CheckMarkTintModeEnum["screen"] = "screen";
        CheckMarkTintModeEnum["src_atop"] = "src_atop";
        CheckMarkTintModeEnum["src_in"] = "src_in";
        CheckMarkTintModeEnum["src_over"] = "src_over";
    })(CheckMarkTintModeEnum = MainTypes.CheckMarkTintModeEnum || (MainTypes.CheckMarkTintModeEnum = {}));
    var ChoiceModeEnum;
    (function (ChoiceModeEnum) {
        ChoiceModeEnum["multipleChoice"] = "multipleChoice";
        ChoiceModeEnum["multipleChoiceModal"] = "multipleChoiceModal";
        ChoiceModeEnum["none"] = "none";
        ChoiceModeEnum["singleChoice"] = "singleChoice";
    })(ChoiceModeEnum = MainTypes.ChoiceModeEnum || (MainTypes.ChoiceModeEnum = {}));
    var DrawableTintModeEnum;
    (function (DrawableTintModeEnum) {
        DrawableTintModeEnum["add"] = "add";
        DrawableTintModeEnum["multiply"] = "multiply";
        DrawableTintModeEnum["screen"] = "screen";
        DrawableTintModeEnum["src_atop"] = "src_atop";
        DrawableTintModeEnum["src_in"] = "src_in";
        DrawableTintModeEnum["src_over"] = "src_over";
    })(DrawableTintModeEnum = MainTypes.DrawableTintModeEnum || (MainTypes.DrawableTintModeEnum = {}));
    var DrawingCacheQualityEnum;
    (function (DrawingCacheQualityEnum) {
        DrawingCacheQualityEnum["auto"] = "auto";
        DrawingCacheQualityEnum["high"] = "high";
        DrawingCacheQualityEnum["low"] = "low";
    })(DrawingCacheQualityEnum = MainTypes.DrawingCacheQualityEnum || (MainTypes.DrawingCacheQualityEnum = {}));
    var DropDownHeightEnum;
    (function (DropDownHeightEnum) {
        DropDownHeightEnum["fill_parent"] = "fill_parent";
        DropDownHeightEnum["match_parent"] = "match_parent";
        DropDownHeightEnum["wrap_content"] = "wrap_content";
    })(DropDownHeightEnum = MainTypes.DropDownHeightEnum || (MainTypes.DropDownHeightEnum = {}));
    var DropDownWidthEnum;
    (function (DropDownWidthEnum) {
        DropDownWidthEnum["fill_parent"] = "fill_parent";
        DropDownWidthEnum["match_parent"] = "match_parent";
        DropDownWidthEnum["wrap_content"] = "wrap_content";
    })(DropDownWidthEnum = MainTypes.DropDownWidthEnum || (MainTypes.DropDownWidthEnum = {}));
    var EllipsizeEnum;
    (function (EllipsizeEnum) {
        EllipsizeEnum["end"] = "end";
        EllipsizeEnum["marquee"] = "marquee";
        EllipsizeEnum["middle"] = "middle";
        EllipsizeEnum["none"] = "none";
        EllipsizeEnum["start"] = "start";
    })(EllipsizeEnum = MainTypes.EllipsizeEnum || (MainTypes.EllipsizeEnum = {}));
    var FocusableEnum;
    (function (FocusableEnum) {
        FocusableEnum["auto"] = "auto";
    })(FocusableEnum = MainTypes.FocusableEnum || (MainTypes.FocusableEnum = {}));
    var ForegroundGravityFlagsEnum;
    (function (ForegroundGravityFlagsEnum) {
        ForegroundGravityFlagsEnum["bottom"] = "bottom";
        ForegroundGravityFlagsEnum["center"] = "center";
        ForegroundGravityFlagsEnum["center_horizontal"] = "center_horizontal";
        ForegroundGravityFlagsEnum["center_vertical"] = "center_vertical";
        ForegroundGravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        ForegroundGravityFlagsEnum["clip_vertical"] = "clip_vertical";
        ForegroundGravityFlagsEnum["fill"] = "fill";
        ForegroundGravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        ForegroundGravityFlagsEnum["fill_vertical"] = "fill_vertical";
        ForegroundGravityFlagsEnum["left"] = "left";
        ForegroundGravityFlagsEnum["right"] = "right";
        ForegroundGravityFlagsEnum["top"] = "top";
    })(ForegroundGravityFlagsEnum = MainTypes.ForegroundGravityFlagsEnum || (MainTypes.ForegroundGravityFlagsEnum = {}));
    var ForegroundTintModeEnum;
    (function (ForegroundTintModeEnum) {
        ForegroundTintModeEnum["add"] = "add";
        ForegroundTintModeEnum["multiply"] = "multiply";
        ForegroundTintModeEnum["screen"] = "screen";
        ForegroundTintModeEnum["src_atop"] = "src_atop";
        ForegroundTintModeEnum["src_in"] = "src_in";
        ForegroundTintModeEnum["src_over"] = "src_over";
    })(ForegroundTintModeEnum = MainTypes.ForegroundTintModeEnum || (MainTypes.ForegroundTintModeEnum = {}));
    var GravityFlagsEnum;
    (function (GravityFlagsEnum) {
        GravityFlagsEnum["bottom"] = "bottom";
        GravityFlagsEnum["center"] = "center";
        GravityFlagsEnum["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum["center_vertical"] = "center_vertical";
        GravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum["end"] = "end";
        GravityFlagsEnum["fill"] = "fill";
        GravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum["left"] = "left";
        GravityFlagsEnum["right"] = "right";
        GravityFlagsEnum["start"] = "start";
        GravityFlagsEnum["top"] = "top";
    })(GravityFlagsEnum = MainTypes.GravityFlagsEnum || (MainTypes.GravityFlagsEnum = {}));
    var GravityFlagsEnum_;
    (function (GravityFlagsEnum_) {
        GravityFlagsEnum_["bottom"] = "bottom";
        GravityFlagsEnum_["center"] = "center";
        GravityFlagsEnum_["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum_["center_vertical"] = "center_vertical";
        GravityFlagsEnum_["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum_["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum_["end"] = "end";
        GravityFlagsEnum_["fill"] = "fill";
        GravityFlagsEnum_["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum_["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum_["left"] = "left";
        GravityFlagsEnum_["right"] = "right";
        GravityFlagsEnum_["start"] = "start";
        GravityFlagsEnum_["top"] = "top";
    })(GravityFlagsEnum_ = MainTypes.GravityFlagsEnum_ || (MainTypes.GravityFlagsEnum_ = {}));
    var GravityFlagsEnum__;
    (function (GravityFlagsEnum__) {
        GravityFlagsEnum__["bottom"] = "bottom";
        GravityFlagsEnum__["center"] = "center";
        GravityFlagsEnum__["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum__["center_vertical"] = "center_vertical";
        GravityFlagsEnum__["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum__["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum__["end"] = "end";
        GravityFlagsEnum__["fill"] = "fill";
        GravityFlagsEnum__["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum__["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum__["left"] = "left";
        GravityFlagsEnum__["right"] = "right";
        GravityFlagsEnum__["start"] = "start";
        GravityFlagsEnum__["top"] = "top";
    })(GravityFlagsEnum__ = MainTypes.GravityFlagsEnum__ || (MainTypes.GravityFlagsEnum__ = {}));
    var GravityFlagsEnum___;
    (function (GravityFlagsEnum___) {
        GravityFlagsEnum___["bottom"] = "bottom";
        GravityFlagsEnum___["center"] = "center";
        GravityFlagsEnum___["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum___["center_vertical"] = "center_vertical";
        GravityFlagsEnum___["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum___["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum___["end"] = "end";
        GravityFlagsEnum___["fill"] = "fill";
        GravityFlagsEnum___["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum___["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum___["left"] = "left";
        GravityFlagsEnum___["right"] = "right";
        GravityFlagsEnum___["start"] = "start";
        GravityFlagsEnum___["top"] = "top";
    })(GravityFlagsEnum___ = MainTypes.GravityFlagsEnum___ || (MainTypes.GravityFlagsEnum___ = {}));
    var GravityFlagsEnum____;
    (function (GravityFlagsEnum____) {
        GravityFlagsEnum____["bottom"] = "bottom";
        GravityFlagsEnum____["center"] = "center";
        GravityFlagsEnum____["center_horizontal"] = "center_horizontal";
        GravityFlagsEnum____["center_vertical"] = "center_vertical";
        GravityFlagsEnum____["clip_horizontal"] = "clip_horizontal";
        GravityFlagsEnum____["clip_vertical"] = "clip_vertical";
        GravityFlagsEnum____["end"] = "end";
        GravityFlagsEnum____["fill"] = "fill";
        GravityFlagsEnum____["fill_horizontal"] = "fill_horizontal";
        GravityFlagsEnum____["fill_vertical"] = "fill_vertical";
        GravityFlagsEnum____["left"] = "left";
        GravityFlagsEnum____["right"] = "right";
        GravityFlagsEnum____["start"] = "start";
        GravityFlagsEnum____["top"] = "top";
    })(GravityFlagsEnum____ = MainTypes.GravityFlagsEnum____ || (MainTypes.GravityFlagsEnum____ = {}));
    var HyphenationFrequencyEnum;
    (function (HyphenationFrequencyEnum) {
        HyphenationFrequencyEnum["full"] = "full";
        HyphenationFrequencyEnum["none"] = "none";
        HyphenationFrequencyEnum["normal"] = "normal";
    })(HyphenationFrequencyEnum = MainTypes.HyphenationFrequencyEnum || (MainTypes.HyphenationFrequencyEnum = {}));
    var ImeOptionsFlagsEnum;
    (function (ImeOptionsFlagsEnum) {
        ImeOptionsFlagsEnum["actionDone"] = "actionDone";
        ImeOptionsFlagsEnum["actionGo"] = "actionGo";
        ImeOptionsFlagsEnum["actionNext"] = "actionNext";
        ImeOptionsFlagsEnum["actionNone"] = "actionNone";
        ImeOptionsFlagsEnum["actionPrevious"] = "actionPrevious";
        ImeOptionsFlagsEnum["actionSearch"] = "actionSearch";
        ImeOptionsFlagsEnum["actionSend"] = "actionSend";
        ImeOptionsFlagsEnum["actionUnspecified"] = "actionUnspecified";
        ImeOptionsFlagsEnum["flagNavigateNext"] = "flagNavigateNext";
        ImeOptionsFlagsEnum["flagNavigatePrevious"] = "flagNavigatePrevious";
        ImeOptionsFlagsEnum["flagNoAccessoryAction"] = "flagNoAccessoryAction";
        ImeOptionsFlagsEnum["flagNoEnterAction"] = "flagNoEnterAction";
        ImeOptionsFlagsEnum["flagNoExtractUi"] = "flagNoExtractUi";
        ImeOptionsFlagsEnum["flagNoFullscreen"] = "flagNoFullscreen";
        ImeOptionsFlagsEnum["flagNoPersonalizedLearning"] = "flagNoPersonalizedLearning";
        ImeOptionsFlagsEnum["normal"] = "normal";
    })(ImeOptionsFlagsEnum = MainTypes.ImeOptionsFlagsEnum || (MainTypes.ImeOptionsFlagsEnum = {}));
    var ImportantForAccessibilityEnum;
    (function (ImportantForAccessibilityEnum) {
        ImportantForAccessibilityEnum["auto"] = "auto";
        ImportantForAccessibilityEnum["no"] = "no";
        ImportantForAccessibilityEnum["noHideDescendants"] = "noHideDescendants";
        ImportantForAccessibilityEnum["yes"] = "yes";
    })(ImportantForAccessibilityEnum = MainTypes.ImportantForAccessibilityEnum || (MainTypes.ImportantForAccessibilityEnum = {}));
    var ImportantForAutofillFlagsEnum;
    (function (ImportantForAutofillFlagsEnum) {
        ImportantForAutofillFlagsEnum["auto"] = "auto";
        ImportantForAutofillFlagsEnum["no"] = "no";
        ImportantForAutofillFlagsEnum["noExcludeDescendants"] = "noExcludeDescendants";
        ImportantForAutofillFlagsEnum["yes"] = "yes";
        ImportantForAutofillFlagsEnum["yesExcludeDescendants"] = "yesExcludeDescendants";
    })(ImportantForAutofillFlagsEnum = MainTypes.ImportantForAutofillFlagsEnum || (MainTypes.ImportantForAutofillFlagsEnum = {}));
    var IndeterminateTintModeEnum;
    (function (IndeterminateTintModeEnum) {
        IndeterminateTintModeEnum["add"] = "add";
        IndeterminateTintModeEnum["multiply"] = "multiply";
        IndeterminateTintModeEnum["screen"] = "screen";
        IndeterminateTintModeEnum["src_atop"] = "src_atop";
        IndeterminateTintModeEnum["src_in"] = "src_in";
        IndeterminateTintModeEnum["src_over"] = "src_over";
    })(IndeterminateTintModeEnum = MainTypes.IndeterminateTintModeEnum || (MainTypes.IndeterminateTintModeEnum = {}));
    var InputTypeFlagsEnum;
    (function (InputTypeFlagsEnum) {
        InputTypeFlagsEnum["date"] = "date";
        InputTypeFlagsEnum["datetime"] = "datetime";
        InputTypeFlagsEnum["none"] = "none";
        InputTypeFlagsEnum["number"] = "number";
        InputTypeFlagsEnum["numberDecimal"] = "numberDecimal";
        InputTypeFlagsEnum["numberPassword"] = "numberPassword";
        InputTypeFlagsEnum["numberSigned"] = "numberSigned";
        InputTypeFlagsEnum["phone"] = "phone";
        InputTypeFlagsEnum["text"] = "text";
        InputTypeFlagsEnum["textAutoComplete"] = "textAutoComplete";
        InputTypeFlagsEnum["textAutoCorrect"] = "textAutoCorrect";
        InputTypeFlagsEnum["textCapCharacters"] = "textCapCharacters";
        InputTypeFlagsEnum["textCapSentences"] = "textCapSentences";
        InputTypeFlagsEnum["textCapWords"] = "textCapWords";
        InputTypeFlagsEnum["textEmailAddress"] = "textEmailAddress";
        InputTypeFlagsEnum["textEmailSubject"] = "textEmailSubject";
        InputTypeFlagsEnum["textFilter"] = "textFilter";
        InputTypeFlagsEnum["textImeMultiLine"] = "textImeMultiLine";
        InputTypeFlagsEnum["textLongMessage"] = "textLongMessage";
        InputTypeFlagsEnum["textMultiLine"] = "textMultiLine";
        InputTypeFlagsEnum["textNoSuggestions"] = "textNoSuggestions";
        InputTypeFlagsEnum["textPassword"] = "textPassword";
        InputTypeFlagsEnum["textPersonName"] = "textPersonName";
        InputTypeFlagsEnum["textPhonetic"] = "textPhonetic";
        InputTypeFlagsEnum["textPostalAddress"] = "textPostalAddress";
        InputTypeFlagsEnum["textShortMessage"] = "textShortMessage";
        InputTypeFlagsEnum["textUri"] = "textUri";
        InputTypeFlagsEnum["textVisiblePassword"] = "textVisiblePassword";
        InputTypeFlagsEnum["textWebEditText"] = "textWebEditText";
        InputTypeFlagsEnum["textWebEmailAddress"] = "textWebEmailAddress";
        InputTypeFlagsEnum["textWebPassword"] = "textWebPassword";
        InputTypeFlagsEnum["time"] = "time";
    })(InputTypeFlagsEnum = MainTypes.InputTypeFlagsEnum || (MainTypes.InputTypeFlagsEnum = {}));
    var JustificationModeEnum;
    (function (JustificationModeEnum) {
        JustificationModeEnum["inter_word"] = "inter_word";
        JustificationModeEnum["none"] = "none";
    })(JustificationModeEnum = MainTypes.JustificationModeEnum || (MainTypes.JustificationModeEnum = {}));
    var LayerTypeEnum;
    (function (LayerTypeEnum) {
        LayerTypeEnum["hardware"] = "hardware";
        LayerTypeEnum["none"] = "none";
        LayerTypeEnum["software"] = "software";
    })(LayerTypeEnum = MainTypes.LayerTypeEnum || (MainTypes.LayerTypeEnum = {}));
    var LayoutDirectionEnum;
    (function (LayoutDirectionEnum) {
        LayoutDirectionEnum["inherit"] = "inherit";
        LayoutDirectionEnum["locale"] = "locale";
        LayoutDirectionEnum["ltr"] = "ltr";
        LayoutDirectionEnum["rtl"] = "rtl";
    })(LayoutDirectionEnum = MainTypes.LayoutDirectionEnum || (MainTypes.LayoutDirectionEnum = {}));
    var LayoutGravityFlagsEnum;
    (function (LayoutGravityFlagsEnum) {
        LayoutGravityFlagsEnum["bottom"] = "bottom";
        LayoutGravityFlagsEnum["center"] = "center";
        LayoutGravityFlagsEnum["center_horizontal"] = "center_horizontal";
        LayoutGravityFlagsEnum["center_vertical"] = "center_vertical";
        LayoutGravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        LayoutGravityFlagsEnum["clip_vertical"] = "clip_vertical";
        LayoutGravityFlagsEnum["end"] = "end";
        LayoutGravityFlagsEnum["fill"] = "fill";
        LayoutGravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        LayoutGravityFlagsEnum["fill_vertical"] = "fill_vertical";
        LayoutGravityFlagsEnum["left"] = "left";
        LayoutGravityFlagsEnum["right"] = "right";
        LayoutGravityFlagsEnum["start"] = "start";
        LayoutGravityFlagsEnum["top"] = "top";
    })(LayoutGravityFlagsEnum = MainTypes.LayoutGravityFlagsEnum || (MainTypes.LayoutGravityFlagsEnum = {}));
    var LayoutGravityFlagsEnum_;
    (function (LayoutGravityFlagsEnum_) {
        LayoutGravityFlagsEnum_["bottom"] = "bottom";
        LayoutGravityFlagsEnum_["center"] = "center";
        LayoutGravityFlagsEnum_["center_horizontal"] = "center_horizontal";
        LayoutGravityFlagsEnum_["center_vertical"] = "center_vertical";
        LayoutGravityFlagsEnum_["clip_horizontal"] = "clip_horizontal";
        LayoutGravityFlagsEnum_["clip_vertical"] = "clip_vertical";
        LayoutGravityFlagsEnum_["end"] = "end";
        LayoutGravityFlagsEnum_["fill"] = "fill";
        LayoutGravityFlagsEnum_["fill_horizontal"] = "fill_horizontal";
        LayoutGravityFlagsEnum_["fill_vertical"] = "fill_vertical";
        LayoutGravityFlagsEnum_["left"] = "left";
        LayoutGravityFlagsEnum_["right"] = "right";
        LayoutGravityFlagsEnum_["start"] = "start";
        LayoutGravityFlagsEnum_["top"] = "top";
    })(LayoutGravityFlagsEnum_ = MainTypes.LayoutGravityFlagsEnum_ || (MainTypes.LayoutGravityFlagsEnum_ = {}));
    var LayoutHeightEnum;
    (function (LayoutHeightEnum) {
        LayoutHeightEnum["fill_parent"] = "fill_parent";
        LayoutHeightEnum["match_parent"] = "match_parent";
        LayoutHeightEnum["wrap_content"] = "wrap_content";
    })(LayoutHeightEnum = MainTypes.LayoutHeightEnum || (MainTypes.LayoutHeightEnum = {}));
    var LayoutModeEnum;
    (function (LayoutModeEnum) {
        LayoutModeEnum["clipBounds"] = "clipBounds";
        LayoutModeEnum["opticalBounds"] = "opticalBounds";
    })(LayoutModeEnum = MainTypes.LayoutModeEnum || (MainTypes.LayoutModeEnum = {}));
    var LayoutWidthEnum;
    (function (LayoutWidthEnum) {
        LayoutWidthEnum["fill_parent"] = "fill_parent";
        LayoutWidthEnum["match_parent"] = "match_parent";
        LayoutWidthEnum["wrap_content"] = "wrap_content";
    })(LayoutWidthEnum = MainTypes.LayoutWidthEnum || (MainTypes.LayoutWidthEnum = {}));
    var MarqueeRepeatLimitEnum;
    (function (MarqueeRepeatLimitEnum) {
        MarqueeRepeatLimitEnum["marquee_forever"] = "marquee_forever";
    })(MarqueeRepeatLimitEnum = MainTypes.MarqueeRepeatLimitEnum || (MainTypes.MarqueeRepeatLimitEnum = {}));
    var NumColumnsEnum;
    (function (NumColumnsEnum) {
        NumColumnsEnum["auto_fit"] = "auto_fit";
    })(NumColumnsEnum = MainTypes.NumColumnsEnum || (MainTypes.NumColumnsEnum = {}));
    var OrientationEnum;
    (function (OrientationEnum) {
        OrientationEnum["horizontal"] = "horizontal";
        OrientationEnum["vertical"] = "vertical";
    })(OrientationEnum = MainTypes.OrientationEnum || (MainTypes.OrientationEnum = {}));
    var OrientationEnum_;
    (function (OrientationEnum_) {
        OrientationEnum_["horizontal"] = "horizontal";
        OrientationEnum_["vertical"] = "vertical";
    })(OrientationEnum_ = MainTypes.OrientationEnum_ || (MainTypes.OrientationEnum_ = {}));
    var OverScrollModeEnum;
    (function (OverScrollModeEnum) {
        OverScrollModeEnum["always"] = "always";
        OverScrollModeEnum["ifContentScrolls"] = "ifContentScrolls";
        OverScrollModeEnum["never"] = "never";
    })(OverScrollModeEnum = MainTypes.OverScrollModeEnum || (MainTypes.OverScrollModeEnum = {}));
    var PersistentDrawingCacheFlagsEnum;
    (function (PersistentDrawingCacheFlagsEnum) {
        PersistentDrawingCacheFlagsEnum["all"] = "all";
        PersistentDrawingCacheFlagsEnum["animation"] = "animation";
        PersistentDrawingCacheFlagsEnum["none"] = "none";
        PersistentDrawingCacheFlagsEnum["scrolling"] = "scrolling";
    })(PersistentDrawingCacheFlagsEnum = MainTypes.PersistentDrawingCacheFlagsEnum || (MainTypes.PersistentDrawingCacheFlagsEnum = {}));
    var ProgressBackgroundTintModeEnum;
    (function (ProgressBackgroundTintModeEnum) {
        ProgressBackgroundTintModeEnum["add"] = "add";
        ProgressBackgroundTintModeEnum["multiply"] = "multiply";
        ProgressBackgroundTintModeEnum["screen"] = "screen";
        ProgressBackgroundTintModeEnum["src_atop"] = "src_atop";
        ProgressBackgroundTintModeEnum["src_in"] = "src_in";
        ProgressBackgroundTintModeEnum["src_over"] = "src_over";
    })(ProgressBackgroundTintModeEnum = MainTypes.ProgressBackgroundTintModeEnum || (MainTypes.ProgressBackgroundTintModeEnum = {}));
    var ProgressTintModeEnum;
    (function (ProgressTintModeEnum) {
        ProgressTintModeEnum["add"] = "add";
        ProgressTintModeEnum["multiply"] = "multiply";
        ProgressTintModeEnum["screen"] = "screen";
        ProgressTintModeEnum["src_atop"] = "src_atop";
        ProgressTintModeEnum["src_in"] = "src_in";
        ProgressTintModeEnum["src_over"] = "src_over";
    })(ProgressTintModeEnum = MainTypes.ProgressTintModeEnum || (MainTypes.ProgressTintModeEnum = {}));
    var ScaleTypeEnum;
    (function (ScaleTypeEnum) {
        ScaleTypeEnum["center"] = "center";
        ScaleTypeEnum["centerCrop"] = "centerCrop";
        ScaleTypeEnum["centerInside"] = "centerInside";
        ScaleTypeEnum["fitCenter"] = "fitCenter";
        ScaleTypeEnum["fitEnd"] = "fitEnd";
        ScaleTypeEnum["fitStart"] = "fitStart";
        ScaleTypeEnum["fitXY"] = "fitXY";
        ScaleTypeEnum["matrix"] = "matrix";
    })(ScaleTypeEnum = MainTypes.ScaleTypeEnum || (MainTypes.ScaleTypeEnum = {}));
    var ScrollIndicatorsFlagsEnum;
    (function (ScrollIndicatorsFlagsEnum) {
        ScrollIndicatorsFlagsEnum["bottom"] = "bottom";
        ScrollIndicatorsFlagsEnum["end"] = "end";
        ScrollIndicatorsFlagsEnum["left"] = "left";
        ScrollIndicatorsFlagsEnum["none"] = "none";
        ScrollIndicatorsFlagsEnum["right"] = "right";
        ScrollIndicatorsFlagsEnum["start"] = "start";
        ScrollIndicatorsFlagsEnum["top"] = "top";
    })(ScrollIndicatorsFlagsEnum = MainTypes.ScrollIndicatorsFlagsEnum || (MainTypes.ScrollIndicatorsFlagsEnum = {}));
    var ScrollbarStyleEnum;
    (function (ScrollbarStyleEnum) {
        ScrollbarStyleEnum["insideInset"] = "insideInset";
        ScrollbarStyleEnum["insideOverlay"] = "insideOverlay";
        ScrollbarStyleEnum["outsideInset"] = "outsideInset";
        ScrollbarStyleEnum["outsideOverlay"] = "outsideOverlay";
    })(ScrollbarStyleEnum = MainTypes.ScrollbarStyleEnum || (MainTypes.ScrollbarStyleEnum = {}));
    var SecondaryProgressTintModeEnum;
    (function (SecondaryProgressTintModeEnum) {
        SecondaryProgressTintModeEnum["add"] = "add";
        SecondaryProgressTintModeEnum["multiply"] = "multiply";
        SecondaryProgressTintModeEnum["screen"] = "screen";
        SecondaryProgressTintModeEnum["src_atop"] = "src_atop";
        SecondaryProgressTintModeEnum["src_in"] = "src_in";
        SecondaryProgressTintModeEnum["src_over"] = "src_over";
    })(SecondaryProgressTintModeEnum = MainTypes.SecondaryProgressTintModeEnum || (MainTypes.SecondaryProgressTintModeEnum = {}));
    var ShowDividersFlagsEnum;
    (function (ShowDividersFlagsEnum) {
        ShowDividersFlagsEnum["beginning"] = "beginning";
        ShowDividersFlagsEnum["end"] = "end";
        ShowDividersFlagsEnum["middle"] = "middle";
        ShowDividersFlagsEnum["none"] = "none";
    })(ShowDividersFlagsEnum = MainTypes.ShowDividersFlagsEnum || (MainTypes.ShowDividersFlagsEnum = {}));
    var StretchModeEnum;
    (function (StretchModeEnum) {
        StretchModeEnum["columnWidth"] = "columnWidth";
        StretchModeEnum["none"] = "none";
        StretchModeEnum["spacingWidth"] = "spacingWidth";
        StretchModeEnum["spacingWidthUniform"] = "spacingWidthUniform";
    })(StretchModeEnum = MainTypes.StretchModeEnum || (MainTypes.StretchModeEnum = {}));
    var TextAlignmentEnum;
    (function (TextAlignmentEnum) {
        TextAlignmentEnum["center"] = "center";
        TextAlignmentEnum["gravity"] = "gravity";
        TextAlignmentEnum["inherit"] = "inherit";
        TextAlignmentEnum["textEnd"] = "textEnd";
        TextAlignmentEnum["textStart"] = "textStart";
        TextAlignmentEnum["viewEnd"] = "viewEnd";
        TextAlignmentEnum["viewStart"] = "viewStart";
    })(TextAlignmentEnum = MainTypes.TextAlignmentEnum || (MainTypes.TextAlignmentEnum = {}));
    var TextDirectionEnum;
    (function (TextDirectionEnum) {
        TextDirectionEnum["anyRtl"] = "anyRtl";
        TextDirectionEnum["firstStrong"] = "firstStrong";
        TextDirectionEnum["firstStrongLtr"] = "firstStrongLtr";
        TextDirectionEnum["firstStrongRtl"] = "firstStrongRtl";
        TextDirectionEnum["inherit"] = "inherit";
        TextDirectionEnum["locale"] = "locale";
        TextDirectionEnum["ltr"] = "ltr";
        TextDirectionEnum["rtl"] = "rtl";
    })(TextDirectionEnum = MainTypes.TextDirectionEnum || (MainTypes.TextDirectionEnum = {}));
    var ThumbTintModeEnum;
    (function (ThumbTintModeEnum) {
        ThumbTintModeEnum["add"] = "add";
        ThumbTintModeEnum["multiply"] = "multiply";
        ThumbTintModeEnum["screen"] = "screen";
        ThumbTintModeEnum["src_atop"] = "src_atop";
        ThumbTintModeEnum["src_in"] = "src_in";
        ThumbTintModeEnum["src_over"] = "src_over";
    })(ThumbTintModeEnum = MainTypes.ThumbTintModeEnum || (MainTypes.ThumbTintModeEnum = {}));
    var TickMarkTintModeEnum;
    (function (TickMarkTintModeEnum) {
        TickMarkTintModeEnum["add"] = "add";
        TickMarkTintModeEnum["multiply"] = "multiply";
        TickMarkTintModeEnum["screen"] = "screen";
        TickMarkTintModeEnum["src_atop"] = "src_atop";
        TickMarkTintModeEnum["src_in"] = "src_in";
        TickMarkTintModeEnum["src_over"] = "src_over";
    })(TickMarkTintModeEnum = MainTypes.TickMarkTintModeEnum || (MainTypes.TickMarkTintModeEnum = {}));
    var TrackTintModeEnum;
    (function (TrackTintModeEnum) {
        TrackTintModeEnum["add"] = "add";
        TrackTintModeEnum["multiply"] = "multiply";
        TrackTintModeEnum["screen"] = "screen";
        TrackTintModeEnum["src_atop"] = "src_atop";
        TrackTintModeEnum["src_in"] = "src_in";
        TrackTintModeEnum["src_over"] = "src_over";
    })(TrackTintModeEnum = MainTypes.TrackTintModeEnum || (MainTypes.TrackTintModeEnum = {}));
    var TranscriptModeEnum;
    (function (TranscriptModeEnum) {
        TranscriptModeEnum["alwaysScroll"] = "alwaysScroll";
        TranscriptModeEnum["disabled"] = "disabled";
        TranscriptModeEnum["normal"] = "normal";
    })(TranscriptModeEnum = MainTypes.TranscriptModeEnum || (MainTypes.TranscriptModeEnum = {}));
    var VerticalScrollbarPositionEnum;
    (function (VerticalScrollbarPositionEnum) {
        VerticalScrollbarPositionEnum["defaultPosition"] = "defaultPosition";
        VerticalScrollbarPositionEnum["left"] = "left";
        VerticalScrollbarPositionEnum["right"] = "right";
    })(VerticalScrollbarPositionEnum = MainTypes.VerticalScrollbarPositionEnum || (MainTypes.VerticalScrollbarPositionEnum = {}));
    var VisibilityEnum;
    (function (VisibilityEnum) {
        VisibilityEnum["gone"] = "gone";
        VisibilityEnum["invisible"] = "invisible";
        VisibilityEnum["visible"] = "visible";
    })(VisibilityEnum = MainTypes.VisibilityEnum || (MainTypes.VisibilityEnum = {}));
})(MainTypes = exports.MainTypes || (exports.MainTypes = {}));
// elements
exports.AdapterViewAnimator = function (attributes, children) {
    return element_1.element('adapterViewAnimator', attributes, children);
};
exports.FrameLayout = function (attributes, children) {
    return element_1.element('frameLayout', attributes, children);
};
exports.View = function (attributes, children) {
    return element_1.element('view', attributes, children);
};
exports.SearchView = function (attributes, children) {
    return element_1.element('searchView', attributes, children);
};
exports.TableRow = function (attributes, children) {
    return element_1.element('tableRow', attributes, children);
};
exports.RelativeLayout = function (attributes, children) {
    return element_1.element('relativeLayout', attributes, children);
};
exports.ImageSwitcher = function (attributes, children) {
    return element_1.element('imageSwitcher', attributes, children);
};
exports.RadioGroup = function (attributes, children) {
    return element_1.element('radioGroup', attributes, children);
};
exports.Toolbar = function (attributes, children) {
    return element_1.element('toolbar', attributes, children);
};
exports.ListView = function (attributes, children) {
    return element_1.element('listView', attributes, children);
};
exports.Spinner = function (attributes, children) {
    return element_1.element('spinner', attributes, children);
};
exports.ScrollView = function (attributes, children) {
    return element_1.element('scrollView', attributes, children);
};
exports.ViewFlipper = function (attributes, children) {
    return element_1.element('viewFlipper', attributes, children);
};
exports.ViewSwitcher = function (attributes, children) {
    return element_1.element('viewSwitcher', attributes, children);
};
exports.DatePicker = function (attributes, children) {
    return element_1.element('datePicker', attributes, children);
};
exports.StackView = function (attributes, children) {
    return element_1.element('stackView', attributes, children);
};
exports.TimePicker = function (attributes, children) {
    return element_1.element('timePicker', attributes, children);
};
exports.MediaController = function (attributes, children) {
    return element_1.element('mediaController', attributes, children);
};
exports.ActionMenuView = function (attributes, children) {
    return element_1.element('actionMenuView', attributes, children);
};
exports.ZoomControls = function (attributes, children) {
    return element_1.element('zoomControls', attributes, children);
};
exports.AdapterView = function (attributes, children) {
    return element_1.element('adapterView', attributes, children);
};
exports.ViewAnimator = function (attributes, children) {
    return element_1.element('viewAnimator', attributes, children);
};
exports.HorizontalScrollView = function (attributes, children) {
    return element_1.element('horizontalScrollView', attributes, children);
};
exports.CalendarView = function (attributes, children) {
    return element_1.element('calendarView', attributes, children);
};
exports.AbsListView = function (attributes, children) {
    return element_1.element('absListView', attributes, children);
};
exports.LinearLayout = function (attributes, children) {
    return element_1.element('linearLayout', attributes, children);
};
exports.NumberPicker = function (attributes, children) {
    return element_1.element('numberPicker', attributes, children);
};
exports.AdapterViewFlipper = function (attributes, children) {
    return element_1.element('adapterViewFlipper', attributes, children);
};
exports.AbsSpinner = function (attributes, children) {
    return element_1.element('absSpinner', attributes, children);
};
exports.ViewGroup = function (attributes, children) {
    return element_1.element('viewGroup', attributes, children);
};
exports.TextSwitcher = function (attributes, children) {
    return element_1.element('textSwitcher', attributes, children);
};
exports.ExpandableListView = function (attributes, children) {
    return element_1.element('expandableListView', attributes, children);
};
exports.GridView = function (attributes, children) {
    return element_1.element('gridView', attributes, children);
};
exports.TableLayout = function (attributes, children) {
    return element_1.element('tableLayout', attributes, children);
};
exports.TabHost = function (attributes, children) {
    return element_1.element('tabHost', attributes, children);
};
exports.GridLayout = function (attributes, children) {
    return element_1.element('gridLayout', attributes, children);
};
exports.TabWidget = function (attributes, children) {
    return element_1.element('tabWidget', attributes, children);
};
exports.Space = function (attributes, children) {
    return element_1.element('space', attributes, children);
};
exports.MultiAutoCompleteTextView = function (attributes, children) {
    return element_1.element('multiAutoCompleteTextView', attributes, children);
};
exports.QuickContactBadge = function (attributes, children) {
    return element_1.element('quickContactBadge', attributes, children);
};
exports.EditText = function (attributes, children) {
    return element_1.element('editText', attributes, children);
};
exports.SurfaceView = function (attributes, children) {
    return element_1.element('surfaceView', attributes, children);
};
exports.RatingBar = function (attributes, children) {
    return element_1.element('ratingBar', attributes, children);
};
exports.ImageView = function (attributes, children) {
    return element_1.element('imageView', attributes, children);
};
exports.AbsSeekBar = function (attributes, children) {
    return element_1.element('absSeekBar', attributes, children);
};
exports.ViewStub = function (attributes, children) {
    return element_1.element('viewStub', attributes, children);
};
exports.CheckedTextView = function (attributes, children) {
    return element_1.element('checkedTextView', attributes, children);
};
exports.TextureView = function (attributes, children) {
    return element_1.element('textureView', attributes, children);
};
exports.ProgressBar = function (attributes, children) {
    return element_1.element('progressBar', attributes, children);
};
exports.TextView = function (attributes, children) {
    return element_1.element('textView', attributes, children);
};
exports.CheckBox = function (attributes, children) {
    return element_1.element('checkBox', attributes, children);
};
exports.Switch = function (attributes, children) {
    return element_1.element('switch', attributes, children);
};
exports.RadioButton = function (attributes, children) {
    return element_1.element('radioButton', attributes, children);
};
exports.SeekBar = function (attributes, children) {
    return element_1.element('seekBar', attributes, children);
};
exports.CompoundButton = function (attributes, children) {
    return element_1.element('compoundButton', attributes, children);
};
exports.ToggleButton = function (attributes, children) {
    return element_1.element('toggleButton', attributes, children);
};
exports.TextClock = function (attributes, children) {
    return element_1.element('textClock', attributes, children);
};
exports.ImageButton = function (attributes, children) {
    return element_1.element('imageButton', attributes, children);
};
exports.Chronometer = function (attributes, children) {
    return element_1.element('chronometer', attributes, children);
};
exports.VideoView = function (attributes, children) {
    return element_1.element('videoView', attributes, children);
};
exports.AutoCompleteTextView = function (attributes, children) {
    return element_1.element('autoCompleteTextView', attributes, children);
};
exports.Button = function (attributes, children) {
    return element_1.element('button', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,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VUFBa0UsRUFDbEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFdBQVcsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDcEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxXQUFXLEdBQUcsVUFDekIsVUFBb0UsRUFDcEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLGFBQWEsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDdEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxXQUFXLEdBQUcsVUFDekIsVUFBb0UsRUFDcEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLGFBQWEsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDdEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxTQUFTLEdBQUcsVUFDdkIsVUFBa0UsRUFDbEUsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLFdBQVcsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDcEQsQ0FBQyxDQUFDO0FBQ1csUUFBQSxvQkFBb0IsR0FBRyxVQUNsQyxVQUE2RSxFQUM3RSxRQUE2RDtJQUU3RCxPQUFPLGlCQUFPLENBQUMsc0JBQXNCLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQy9ELENBQUMsQ0FBQztBQUNXLFFBQUEsTUFBTSxHQUFHLFVBQ3BCLFVBQStELEVBQy9ELFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxRQUFRLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ2pELENBQUMsQ0FBQyIsIm5hbWVzIjpbXSwic291cmNlcyI6WyIvVXNlcnMvZGVyay9qc1Byb2plY3RzL3NvdXJjZXJlci9qcy9tYWluLnRzIl0sInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IEVsZW1lbnROb2RlLCBlbGVtZW50IH0gZnJvbSAnLi9lbGVtZW50JztcbmltcG9ydCB7IExheW91dFBhcmFtQXR0cmlidXRlcyB9IGZyb20gJy4vbGF5b3V0cGFyYW1zJztcbi8vIHR5cGVzXG4vKiBnZW5lcmF0ZWQgQCAyMDE4LTExLTEyVDEzOjI5OjM4LjUwNiAqL1xuZXhwb3J0IG5hbWVzcGFjZSBNYWluVHlwZXMge1xuICBleHBvcnQgaW50ZXJmYWNlIEFic0xpc3RWaWV3QXR0cmlidXRlcyBleHRlbmRzIEFkYXB0ZXJWaWV3QXR0cmlidXRlcyB7XG4gICAgY2FjaGVDb2xvckhpbnQ/OiBzdHJpbmc7XG4gICAgY2hvaWNlTW9kZT86IENob2ljZU1vZGVFbnVtO1xuICAgIGRyYXdTZWxlY3Rvck9uVG9wPzogYm9vbGVhbjtcbiAgICBmYXN0U2Nyb2xsQWx3YXlzVmlzaWJsZT86IGJvb2xlYW47XG4gICAgZmFzdFNjcm9sbEVuYWJsZWQ/OiBib29sZWFuO1xuICAgIGZhc3RTY3JvbGxTdHlsZT86IHN0cmluZztcbiAgICBsaXN0U2VsZWN0b3I/OiBzdHJpbmc7XG4gICAgc2Nyb2xsaW5nQ2FjaGU/OiBib29sZWFuO1xuICAgIHNtb290aFNjcm9sbGJhcj86IGJvb2xlYW47XG4gICAgc3RhY2tGcm9tQm90dG9tPzogYm9vbGVhbjtcbiAgICB0ZXh0RmlsdGVyRW5hYmxlZD86IGJvb2xlYW47XG4gICAgdHJhbnNjcmlwdE1vZGU/OiBUcmFuc2NyaXB0TW9kZUVudW07XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBYnNMaXN0Vmlld0xheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEFic1NlZWtCYXJBdHRyaWJ1dGVzIGV4dGVuZHMgUHJvZ3Jlc3NCYXJBdHRyaWJ1dGVzIHtcbiAgICBTZWVrQmFyX3NwbGl0VHJhY2s/OiBib29sZWFuO1xuICAgIFNlZWtCYXJfdGh1bWI/OiBudW1iZXI7XG4gICAgU2Vla0Jhcl90aHVtYk9mZnNldD86IHN0cmluZztcbiAgICBTZWVrQmFyX3RodW1iVGludD86IG51bWJlcjtcbiAgICBTZWVrQmFyX3RodW1iVGludE1vZGU/OiBudW1iZXI7XG4gICAgU2Vla0Jhcl90aWNrTWFyaz86IG51bWJlcjtcbiAgICBTZWVrQmFyX3RpY2tNYXJrVGludD86IG51bWJlcjtcbiAgICBTZWVrQmFyX3RpY2tNYXJrVGludE1vZGU/OiBudW1iZXI7XG4gICAgdGh1bWJUaW50Pzogc3RyaW5nO1xuICAgIHRodW1iVGludE1vZGU/OiBUaHVtYlRpbnRNb2RlRW51bTtcbiAgICB0aWNrTWFya1RpbnQ/OiBzdHJpbmc7XG4gICAgdGlja01hcmtUaW50TW9kZT86IFRpY2tNYXJrVGludE1vZGVFbnVtO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQWJzU3Bpbm5lckF0dHJpYnV0ZXMgZXh0ZW5kcyBBZGFwdGVyVmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIEFjY2Vzc2liaWxpdHlMaXZlUmVnaW9uRW51bSB7IGFzc2VydGl2ZSA9ICdhc3NlcnRpdmUnLCBub25lID0gJ25vbmUnLCBwb2xpdGUgPSAncG9saXRlJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQWN0aW9uTWVudVZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBY3Rpb25NZW51Vmlld0xheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEFkYXB0ZXJWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzIGV4dGVuZHMgQWRhcHRlclZpZXdBdHRyaWJ1dGVzIHtcbiAgICBhbmltYXRlRmlyc3RWaWV3PzogYm9vbGVhbjtcbiAgICBpbkFuaW1hdGlvbj86IG51bWJlcjtcbiAgICBvdXRBbmltYXRpb24/OiBudW1iZXI7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBZGFwdGVyVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEFkYXB0ZXJWaWV3RmxpcHBlckF0dHJpYnV0ZXMgZXh0ZW5kcyBBZGFwdGVyVmlld0FuaW1hdG9yQXR0cmlidXRlcyB7XG4gICAgYXV0b1N0YXJ0PzogYm9vbGVhbjtcbiAgICBmbGlwSW50ZXJ2YWw/OiBudW1iZXI7XG4gIH1cbiAgZXhwb3J0IGVudW0gQWxpZ25tZW50TW9kZUVudW0geyBhbGlnbkJvdW5kcyA9ICdhbGlnbkJvdW5kcycsIGFsaWduTWFyZ2lucyA9ICdhbGlnbk1hcmdpbnMnIH1cbiAgZXhwb3J0IGludGVyZmFjZSBBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBFZGl0VGV4dEF0dHJpYnV0ZXMge1xuICAgIGNvbXBsZXRpb25IaW50Pzogc3RyaW5nO1xuICAgIGNvbXBsZXRpb25UaHJlc2hvbGQ/OiBudW1iZXI7XG4gICAgZHJvcERvd25BbmNob3I/OiBzdHJpbmc7XG4gICAgZHJvcERvd25IZWlnaHQ/OiBzdHJpbmcgfCBEcm9wRG93bkhlaWdodEVudW07XG4gICAgZHJvcERvd25Ib3Jpem9udGFsT2Zmc2V0PzogbnVtYmVyO1xuICAgIGRyb3BEb3duVmVydGljYWxPZmZzZXQ/OiBudW1iZXI7XG4gICAgZHJvcERvd25XaWR0aD86IHN0cmluZyB8IERyb3BEb3duV2lkdGhFbnVtO1xuICB9XG4gIGV4cG9ydCBlbnVtIEF1dG9MaW5rRmxhZ3NFbnVtIHsgYWxsID0gJ2FsbCcsIGVtYWlsID0gJ2VtYWlsJywgbWFwID0gJ21hcCcsIG5vbmUgPSAnbm9uZScsIHBob25lID0gJ3Bob25lJywgd2ViID0gJ3dlYicgfVxuICBleHBvcnQgZW51bSBBdXRvU2l6ZVRleHRUeXBlRW51bSB7IG5vbmUgPSAnbm9uZScsIHVuaWZvcm0gPSAndW5pZm9ybScgfVxuICBleHBvcnQgZW51bSBCYWNrZ3JvdW5kVGludE1vZGVFbnVtIHsgYWRkID0gJ2FkZCcsIG11bHRpcGx5ID0gJ211bHRpcGx5Jywgc2NyZWVuID0gJ3NjcmVlbicsIHNyY19hdG9wID0gJ3NyY19hdG9wJywgc3JjX2luID0gJ3NyY19pbicsIHNyY19vdmVyID0gJ3NyY19vdmVyJyB9XG4gIGV4cG9ydCBlbnVtIEJyZWFrU3RyYXRlZ3lFbnVtIHsgYmFsYW5jZWQgPSAnYmFsYW5jZWQnLCBoaWdoX3F1YWxpdHkgPSAnaGlnaF9xdWFsaXR5Jywgc2ltcGxlID0gJ3NpbXBsZScgfVxuICBleHBvcnQgaW50ZXJmYWNlIEJ1dHRvbkF0dHJpYnV0ZXMgZXh0ZW5kcyBUZXh0Vmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIEJ1dHRvblRpbnRNb2RlRW51bSB7IGFkZCA9ICdhZGQnLCBtdWx0aXBseSA9ICdtdWx0aXBseScsIHNjcmVlbiA9ICdzY3JlZW4nLCBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsIHNyY19pbiA9ICdzcmNfaW4nLCBzcmNfb3ZlciA9ICdzcmNfb3ZlcicgfVxuICBleHBvcnQgaW50ZXJmYWNlIENhbGVuZGFyVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICAgIGRhdGVUZXh0QXBwZWFyYW5jZT86IHN0cmluZztcbiAgICBmaXJzdERheU9mV2Vlaz86IG51bWJlcjtcbiAgICBmb2N1c2VkTW9udGhEYXRlQ29sb3I/OiBzdHJpbmc7XG4gICAgbWF4RGF0ZT86IG51bWJlcjtcbiAgICBtaW5EYXRlPzogbnVtYmVyO1xuICAgIHNlbGVjdGVkRGF0ZVZlcnRpY2FsQmFyPzogc3RyaW5nO1xuICAgIHNlbGVjdGVkV2Vla0JhY2tncm91bmRDb2xvcj86IHN0cmluZztcbiAgICBzaG93V2Vla051bWJlcj86IGJvb2xlYW47XG4gICAgc2hvd25XZWVrQ291bnQ/OiBudW1iZXI7XG4gICAgdW5mb2N1c2VkTW9udGhEYXRlQ29sb3I/OiBzdHJpbmc7XG4gICAgd2Vla0RheVRleHRBcHBlYXJhbmNlPzogc3RyaW5nO1xuICAgIHdlZWtOdW1iZXJDb2xvcj86IHN0cmluZztcbiAgICB3ZWVrU2VwYXJhdG9yTGluZUNvbG9yPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgQ2hlY2tCb3hBdHRyaWJ1dGVzIGV4dGVuZHMgQ29tcG91bmRCdXR0b25BdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBDaGVja01hcmtUaW50TW9kZUVudW0geyBhZGQgPSAnYWRkJywgbXVsdGlwbHkgPSAnbXVsdGlwbHknLCBzY3JlZW4gPSAnc2NyZWVuJywgc3JjX2F0b3AgPSAnc3JjX2F0b3AnLCBzcmNfaW4gPSAnc3JjX2luJywgc3JjX292ZXIgPSAnc3JjX292ZXInIH1cbiAgZXhwb3J0IGludGVyZmFjZSBDaGVja2VkVGV4dFZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVGV4dFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBjaGVja01hcms/OiBzdHJpbmc7XG4gICAgY2hlY2tNYXJrVGludD86IHN0cmluZztcbiAgICBjaGVja01hcmtUaW50TW9kZT86IENoZWNrTWFya1RpbnRNb2RlRW51bTtcbiAgICBjaGVja2VkPzogYm9vbGVhbjtcbiAgfVxuICBleHBvcnQgZW51bSBDaG9pY2VNb2RlRW51bSB7IG11bHRpcGxlQ2hvaWNlID0gJ211bHRpcGxlQ2hvaWNlJywgbXVsdGlwbGVDaG9pY2VNb2RhbCA9ICdtdWx0aXBsZUNob2ljZU1vZGFsJywgbm9uZSA9ICdub25lJywgc2luZ2xlQ2hvaWNlID0gJ3NpbmdsZUNob2ljZScgfVxuICBleHBvcnQgaW50ZXJmYWNlIENocm9ub21ldGVyQXR0cmlidXRlcyBleHRlbmRzIFRleHRWaWV3QXR0cmlidXRlcyB7XG4gICAgY291bnREb3duPzogYm9vbGVhbjtcbiAgICBmb3JtYXQ/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBDb21wb3VuZEJ1dHRvbkF0dHJpYnV0ZXMgZXh0ZW5kcyBCdXR0b25BdHRyaWJ1dGVzIHtcbiAgICBidXR0b24/OiBzdHJpbmc7XG4gICAgYnV0dG9uVGludD86IHN0cmluZztcbiAgICBidXR0b25UaW50TW9kZT86IEJ1dHRvblRpbnRNb2RlRW51bTtcbiAgICBjaGVja2VkPzogYm9vbGVhbjtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIERhdGVQaWNrZXJBdHRyaWJ1dGVzIGV4dGVuZHMgRnJhbWVMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgICBmaXJzdERheU9mV2Vlaz86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgZW51bSBEcmF3YWJsZVRpbnRNb2RlRW51bSB7IGFkZCA9ICdhZGQnLCBtdWx0aXBseSA9ICdtdWx0aXBseScsIHNjcmVlbiA9ICdzY3JlZW4nLCBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsIHNyY19pbiA9ICdzcmNfaW4nLCBzcmNfb3ZlciA9ICdzcmNfb3ZlcicgfVxuICBleHBvcnQgZW51bSBEcmF3aW5nQ2FjaGVRdWFsaXR5RW51bSB7IGF1dG8gPSAnYXV0bycsIGhpZ2ggPSAnaGlnaCcsIGxvdyA9ICdsb3cnIH1cbiAgZXhwb3J0IGVudW0gRHJvcERvd25IZWlnaHRFbnVtIHsgZmlsbF9wYXJlbnQgPSAnZmlsbF9wYXJlbnQnLCBtYXRjaF9wYXJlbnQgPSAnbWF0Y2hfcGFyZW50Jywgd3JhcF9jb250ZW50ID0gJ3dyYXBfY29udGVudCcgfVxuICBleHBvcnQgZW51bSBEcm9wRG93bldpZHRoRW51bSB7IGZpbGxfcGFyZW50ID0gJ2ZpbGxfcGFyZW50JywgbWF0Y2hfcGFyZW50ID0gJ21hdGNoX3BhcmVudCcsIHdyYXBfY29udGVudCA9ICd3cmFwX2NvbnRlbnQnIH1cbiAgZXhwb3J0IGludGVyZmFjZSBFZGl0VGV4dEF0dHJpYnV0ZXMgZXh0ZW5kcyBUZXh0Vmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIEVsbGlwc2l6ZUVudW0geyBlbmQgPSAnZW5kJywgbWFycXVlZSA9ICdtYXJxdWVlJywgbWlkZGxlID0gJ21pZGRsZScsIG5vbmUgPSAnbm9uZScsIHN0YXJ0ID0gJ3N0YXJ0JyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgRXhwYW5kYWJsZUxpc3RWaWV3QXR0cmlidXRlcyBleHRlbmRzIExpc3RWaWV3QXR0cmlidXRlcyB7XG4gICAgY2hpbGREaXZpZGVyPzogc3RyaW5nO1xuICAgIGNoaWxkSW5kaWNhdG9yPzogc3RyaW5nO1xuICAgIGdyb3VwSW5kaWNhdG9yPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBlbnVtIEZvY3VzYWJsZUVudW0geyBhdXRvID0gJ2F1dG8nIH1cbiAgZXhwb3J0IGVudW0gRm9yZWdyb3VuZEdyYXZpdHlGbGFnc0VudW0geyBib3R0b20gPSAnYm90dG9tJywgY2VudGVyID0gJ2NlbnRlcicsIGNlbnRlcl9ob3Jpem9udGFsID0gJ2NlbnRlcl9ob3Jpem9udGFsJywgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsIGNsaXBfaG9yaXpvbnRhbCA9ICdjbGlwX2hvcml6b250YWwnLCBjbGlwX3ZlcnRpY2FsID0gJ2NsaXBfdmVydGljYWwnLCBmaWxsID0gJ2ZpbGwnLCBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJywgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJywgbGVmdCA9ICdsZWZ0JywgcmlnaHQgPSAncmlnaHQnLCB0b3AgPSAndG9wJyB9XG4gIGV4cG9ydCBlbnVtIEZvcmVncm91bmRUaW50TW9kZUVudW0geyBhZGQgPSAnYWRkJywgbXVsdGlwbHkgPSAnbXVsdGlwbHknLCBzY3JlZW4gPSAnc2NyZWVuJywgc3JjX2F0b3AgPSAnc3JjX2F0b3AnLCBzcmNfaW4gPSAnc3JjX2luJywgc3JjX292ZXIgPSAnc3JjX292ZXInIH1cbiAgZXhwb3J0IGludGVyZmFjZSBGcmFtZUxheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgICBtZWFzdXJlQWxsQ2hpbGRyZW4/OiBib29sZWFuO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgRnJhbWVMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwTWFyZ2luTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gICAgbGF5b3V0X2dyYXZpdHk/OiBMYXlvdXRHcmF2aXR5RmxhZ3NFbnVtW107XG4gIH1cbiAgZXhwb3J0IGVudW0gR3Jhdml0eUZsYWdzRW51bSB7IGJvdHRvbSA9ICdib3R0b20nLCBjZW50ZXIgPSAnY2VudGVyJywgY2VudGVyX2hvcml6b250YWwgPSAnY2VudGVyX2hvcml6b250YWwnLCBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJywgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsIGNsaXBfdmVydGljYWwgPSAnY2xpcF92ZXJ0aWNhbCcsIGVuZCA9ICdlbmQnLCBmaWxsID0gJ2ZpbGwnLCBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJywgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJywgbGVmdCA9ICdsZWZ0JywgcmlnaHQgPSAncmlnaHQnLCBzdGFydCA9ICdzdGFydCcsIHRvcCA9ICd0b3AnIH1cbiAgZXhwb3J0IGVudW0gR3Jhdml0eUZsYWdzRW51bV8geyBib3R0b20gPSAnYm90dG9tJywgY2VudGVyID0gJ2NlbnRlcicsIGNlbnRlcl9ob3Jpem9udGFsID0gJ2NlbnRlcl9ob3Jpem9udGFsJywgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsIGNsaXBfaG9yaXpvbnRhbCA9ICdjbGlwX2hvcml6b250YWwnLCBjbGlwX3ZlcnRpY2FsID0gJ2NsaXBfdmVydGljYWwnLCBlbmQgPSAnZW5kJywgZmlsbCA9ICdmaWxsJywgZmlsbF9ob3Jpem9udGFsID0gJ2ZpbGxfaG9yaXpvbnRhbCcsIGZpbGxfdmVydGljYWwgPSAnZmlsbF92ZXJ0aWNhbCcsIGxlZnQgPSAnbGVmdCcsIHJpZ2h0ID0gJ3JpZ2h0Jywgc3RhcnQgPSAnc3RhcnQnLCB0b3AgPSAndG9wJyB9XG4gIGV4cG9ydCBlbnVtIEdyYXZpdHlGbGFnc0VudW1fXyB7IGJvdHRvbSA9ICdib3R0b20nLCBjZW50ZXIgPSAnY2VudGVyJywgY2VudGVyX2hvcml6b250YWwgPSAnY2VudGVyX2hvcml6b250YWwnLCBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJywgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsIGNsaXBfdmVydGljYWwgPSAnY2xpcF92ZXJ0aWNhbCcsIGVuZCA9ICdlbmQnLCBmaWxsID0gJ2ZpbGwnLCBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJywgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJywgbGVmdCA9ICdsZWZ0JywgcmlnaHQgPSAncmlnaHQnLCBzdGFydCA9ICdzdGFydCcsIHRvcCA9ICd0b3AnIH1cbiAgZXhwb3J0IGVudW0gR3Jhdml0eUZsYWdzRW51bV9fXyB7IGJvdHRvbSA9ICdib3R0b20nLCBjZW50ZXIgPSAnY2VudGVyJywgY2VudGVyX2hvcml6b250YWwgPSAnY2VudGVyX2hvcml6b250YWwnLCBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJywgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsIGNsaXBfdmVydGljYWwgPSAnY2xpcF92ZXJ0aWNhbCcsIGVuZCA9ICdlbmQnLCBmaWxsID0gJ2ZpbGwnLCBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJywgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJywgbGVmdCA9ICdsZWZ0JywgcmlnaHQgPSAncmlnaHQnLCBzdGFydCA9ICdzdGFydCcsIHRvcCA9ICd0b3AnIH1cbiAgZXhwb3J0IGVudW0gR3Jhdml0eUZsYWdzRW51bV9fX18geyBib3R0b20gPSAnYm90dG9tJywgY2VudGVyID0gJ2NlbnRlcicsIGNlbnRlcl9ob3Jpem9udGFsID0gJ2NlbnRlcl9ob3Jpem9udGFsJywgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsIGNsaXBfaG9yaXpvbnRhbCA9ICdjbGlwX2hvcml6b250YWwnLCBjbGlwX3ZlcnRpY2FsID0gJ2NsaXBfdmVydGljYWwnLCBlbmQgPSAnZW5kJywgZmlsbCA9ICdmaWxsJywgZmlsbF9ob3Jpem9udGFsID0gJ2ZpbGxfaG9yaXpvbnRhbCcsIGZpbGxfdmVydGljYWwgPSAnZmlsbF92ZXJ0aWNhbCcsIGxlZnQgPSAnbGVmdCcsIHJpZ2h0ID0gJ3JpZ2h0Jywgc3RhcnQgPSAnc3RhcnQnLCB0b3AgPSAndG9wJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgR3JpZExheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgICBhbGlnbm1lbnRNb2RlPzogQWxpZ25tZW50TW9kZUVudW07XG4gICAgY29sdW1uQ291bnQ/OiBudW1iZXI7XG4gICAgY29sdW1uT3JkZXJQcmVzZXJ2ZWQ/OiBib29sZWFuO1xuICAgIG9yaWVudGF0aW9uPzogT3JpZW50YXRpb25FbnVtXztcbiAgICByb3dDb3VudD86IG51bWJlcjtcbiAgICByb3dPcmRlclByZXNlcnZlZD86IGJvb2xlYW47XG4gICAgdXNlRGVmYXVsdE1hcmdpbnM/OiBib29sZWFuO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgR3JpZExheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEdyaWRWaWV3QXR0cmlidXRlcyBleHRlbmRzIEFic0xpc3RWaWV3QXR0cmlidXRlcyB7XG4gICAgY29sdW1uV2lkdGg/OiBzdHJpbmc7XG4gICAgZ3Jhdml0eT86IG51bWJlciB8IEdyYXZpdHlGbGFnc0VudW1fX19fW107XG4gICAgaG9yaXpvbnRhbFNwYWNpbmc/OiBzdHJpbmc7XG4gICAgbnVtQ29sdW1ucz86IG51bWJlciB8IE51bUNvbHVtbnNFbnVtO1xuICAgIHN0cmV0Y2hNb2RlPzogU3RyZXRjaE1vZGVFbnVtO1xuICAgIHZlcnRpY2FsU3BhY2luZz86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIEhvcml6b250YWxTY3JvbGxWaWV3QXR0cmlidXRlcyBleHRlbmRzIEZyYW1lTGF5b3V0QXR0cmlidXRlcyB7XG4gICAgZmlsbFZpZXdwb3J0PzogYm9vbGVhbjtcbiAgfVxuICBleHBvcnQgZW51bSBIeXBoZW5hdGlvbkZyZXF1ZW5jeUVudW0geyBmdWxsID0gJ2Z1bGwnLCBub25lID0gJ25vbmUnLCBub3JtYWwgPSAnbm9ybWFsJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgSW1hZ2VCdXR0b25BdHRyaWJ1dGVzIGV4dGVuZHMgSW1hZ2VWaWV3QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBJbWFnZVN3aXRjaGVyQXR0cmlidXRlcyBleHRlbmRzIFZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgSW1hZ2VWaWV3QXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBhZGp1c3RWaWV3Qm91bmRzPzogYm9vbGVhbjtcbiAgICBiYXNlbGluZT86IHN0cmluZztcbiAgICBiYXNlbGluZUFsaWduQm90dG9tPzogYm9vbGVhbjtcbiAgICBjcm9wVG9QYWRkaW5nPzogYm9vbGVhbjtcbiAgICBkcmF3YWJsZUFscGhhPzogbnVtYmVyO1xuICAgIG1heEhlaWdodD86IHN0cmluZztcbiAgICBtYXhXaWR0aD86IHN0cmluZztcbiAgICBzY2FsZVR5cGU/OiBTY2FsZVR5cGVFbnVtO1xuICAgIHNyYz86IHN0cmluZztcbiAgICB0aW50Pzogc3RyaW5nO1xuICAgIHRpbnRNb2RlPzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBlbnVtIEltZU9wdGlvbnNGbGFnc0VudW0geyBhY3Rpb25Eb25lID0gJ2FjdGlvbkRvbmUnLCBhY3Rpb25HbyA9ICdhY3Rpb25HbycsIGFjdGlvbk5leHQgPSAnYWN0aW9uTmV4dCcsIGFjdGlvbk5vbmUgPSAnYWN0aW9uTm9uZScsIGFjdGlvblByZXZpb3VzID0gJ2FjdGlvblByZXZpb3VzJywgYWN0aW9uU2VhcmNoID0gJ2FjdGlvblNlYXJjaCcsIGFjdGlvblNlbmQgPSAnYWN0aW9uU2VuZCcsIGFjdGlvblVuc3BlY2lmaWVkID0gJ2FjdGlvblVuc3BlY2lmaWVkJywgZmxhZ05hdmlnYXRlTmV4dCA9ICdmbGFnTmF2aWdhdGVOZXh0JywgZmxhZ05hdmlnYXRlUHJldmlvdXMgPSAnZmxhZ05hdmlnYXRlUHJldmlvdXMnLCBmbGFnTm9BY2Nlc3NvcnlBY3Rpb24gPSAnZmxhZ05vQWNjZXNzb3J5QWN0aW9uJywgZmxhZ05vRW50ZXJBY3Rpb24gPSAnZmxhZ05vRW50ZXJBY3Rpb24nLCBmbGFnTm9FeHRyYWN0VWkgPSAnZmxhZ05vRXh0cmFjdFVpJywgZmxhZ05vRnVsbHNjcmVlbiA9ICdmbGFnTm9GdWxsc2NyZWVuJywgZmxhZ05vUGVyc29uYWxpemVkTGVhcm5pbmcgPSAnZmxhZ05vUGVyc29uYWxpemVkTGVhcm5pbmcnLCBub3JtYWwgPSAnbm9ybWFsJyB9XG4gIGV4cG9ydCBlbnVtIEltcG9ydGFudEZvckFjY2Vzc2liaWxpdHlFbnVtIHsgYXV0byA9ICdhdXRvJywgbm8gPSAnbm8nLCBub0hpZGVEZXNjZW5kYW50cyA9ICdub0hpZGVEZXNjZW5kYW50cycsIHllcyA9ICd5ZXMnIH1cbiAgZXhwb3J0IGVudW0gSW1wb3J0YW50Rm9yQXV0b2ZpbGxGbGFnc0VudW0geyBhdXRvID0gJ2F1dG8nLCBubyA9ICdubycsIG5vRXhjbHVkZURlc2NlbmRhbnRzID0gJ25vRXhjbHVkZURlc2NlbmRhbnRzJywgeWVzID0gJ3llcycsIHllc0V4Y2x1ZGVEZXNjZW5kYW50cyA9ICd5ZXNFeGNsdWRlRGVzY2VuZGFudHMnIH1cbiAgZXhwb3J0IGVudW0gSW5kZXRlcm1pbmF0ZVRpbnRNb2RlRW51bSB7IGFkZCA9ICdhZGQnLCBtdWx0aXBseSA9ICdtdWx0aXBseScsIHNjcmVlbiA9ICdzY3JlZW4nLCBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsIHNyY19pbiA9ICdzcmNfaW4nLCBzcmNfb3ZlciA9ICdzcmNfb3ZlcicgfVxuICBleHBvcnQgZW51bSBJbnB1dFR5cGVGbGFnc0VudW0geyBkYXRlID0gJ2RhdGUnLCBkYXRldGltZSA9ICdkYXRldGltZScsIG5vbmUgPSAnbm9uZScsIG51bWJlciA9ICdudW1iZXInLCBudW1iZXJEZWNpbWFsID0gJ251bWJlckRlY2ltYWwnLCBudW1iZXJQYXNzd29yZCA9ICdudW1iZXJQYXNzd29yZCcsIG51bWJlclNpZ25lZCA9ICdudW1iZXJTaWduZWQnLCBwaG9uZSA9ICdwaG9uZScsIHRleHQgPSAndGV4dCcsIHRleHRBdXRvQ29tcGxldGUgPSAndGV4dEF1dG9Db21wbGV0ZScsIHRleHRBdXRvQ29ycmVjdCA9ICd0ZXh0QXV0b0NvcnJlY3QnLCB0ZXh0Q2FwQ2hhcmFjdGVycyA9ICd0ZXh0Q2FwQ2hhcmFjdGVycycsIHRleHRDYXBTZW50ZW5jZXMgPSAndGV4dENhcFNlbnRlbmNlcycsIHRleHRDYXBXb3JkcyA9ICd0ZXh0Q2FwV29yZHMnLCB0ZXh0RW1haWxBZGRyZXNzID0gJ3RleHRFbWFpbEFkZHJlc3MnLCB0ZXh0RW1haWxTdWJqZWN0ID0gJ3RleHRFbWFpbFN1YmplY3QnLCB0ZXh0RmlsdGVyID0gJ3RleHRGaWx0ZXInLCB0ZXh0SW1lTXVsdGlMaW5lID0gJ3RleHRJbWVNdWx0aUxpbmUnLCB0ZXh0TG9uZ01lc3NhZ2UgPSAndGV4dExvbmdNZXNzYWdlJywgdGV4dE11bHRpTGluZSA9ICd0ZXh0TXVsdGlMaW5lJywgdGV4dE5vU3VnZ2VzdGlvbnMgPSAndGV4dE5vU3VnZ2VzdGlvbnMnLCB0ZXh0UGFzc3dvcmQgPSAndGV4dFBhc3N3b3JkJywgdGV4dFBlcnNvbk5hbWUgPSAndGV4dFBlcnNvbk5hbWUnLCB0ZXh0UGhvbmV0aWMgPSAndGV4dFBob25ldGljJywgdGV4dFBvc3RhbEFkZHJlc3MgPSAndGV4dFBvc3RhbEFkZHJlc3MnLCB0ZXh0U2hvcnRNZXNzYWdlID0gJ3RleHRTaG9ydE1lc3NhZ2UnLCB0ZXh0VXJpID0gJ3RleHRVcmknLCB0ZXh0VmlzaWJsZVBhc3N3b3JkID0gJ3RleHRWaXNpYmxlUGFzc3dvcmQnLCB0ZXh0V2ViRWRpdFRleHQgPSAndGV4dFdlYkVkaXRUZXh0JywgdGV4dFdlYkVtYWlsQWRkcmVzcyA9ICd0ZXh0V2ViRW1haWxBZGRyZXNzJywgdGV4dFdlYlBhc3N3b3JkID0gJ3RleHRXZWJQYXNzd29yZCcsIHRpbWUgPSAndGltZScgfVxuICBleHBvcnQgZW51bSBKdXN0aWZpY2F0aW9uTW9kZUVudW0geyBpbnRlcl93b3JkID0gJ2ludGVyX3dvcmQnLCBub25lID0gJ25vbmUnIH1cbiAgZXhwb3J0IGVudW0gTGF5ZXJUeXBlRW51bSB7IGhhcmR3YXJlID0gJ2hhcmR3YXJlJywgbm9uZSA9ICdub25lJywgc29mdHdhcmUgPSAnc29mdHdhcmUnIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0RGlyZWN0aW9uRW51bSB7IGluaGVyaXQgPSAnaW5oZXJpdCcsIGxvY2FsZSA9ICdsb2NhbGUnLCBsdHIgPSAnbHRyJywgcnRsID0gJ3J0bCcgfVxuICBleHBvcnQgZW51bSBMYXlvdXRHcmF2aXR5RmxhZ3NFbnVtIHsgYm90dG9tID0gJ2JvdHRvbScsIGNlbnRlciA9ICdjZW50ZXInLCBjZW50ZXJfaG9yaXpvbnRhbCA9ICdjZW50ZXJfaG9yaXpvbnRhbCcsIGNlbnRlcl92ZXJ0aWNhbCA9ICdjZW50ZXJfdmVydGljYWwnLCBjbGlwX2hvcml6b250YWwgPSAnY2xpcF9ob3Jpem9udGFsJywgY2xpcF92ZXJ0aWNhbCA9ICdjbGlwX3ZlcnRpY2FsJywgZW5kID0gJ2VuZCcsIGZpbGwgPSAnZmlsbCcsIGZpbGxfaG9yaXpvbnRhbCA9ICdmaWxsX2hvcml6b250YWwnLCBmaWxsX3ZlcnRpY2FsID0gJ2ZpbGxfdmVydGljYWwnLCBsZWZ0ID0gJ2xlZnQnLCByaWdodCA9ICdyaWdodCcsIHN0YXJ0ID0gJ3N0YXJ0JywgdG9wID0gJ3RvcCcgfVxuICBleHBvcnQgZW51bSBMYXlvdXRHcmF2aXR5RmxhZ3NFbnVtXyB7IGJvdHRvbSA9ICdib3R0b20nLCBjZW50ZXIgPSAnY2VudGVyJywgY2VudGVyX2hvcml6b250YWwgPSAnY2VudGVyX2hvcml6b250YWwnLCBjZW50ZXJfdmVydGljYWwgPSAnY2VudGVyX3ZlcnRpY2FsJywgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsIGNsaXBfdmVydGljYWwgPSAnY2xpcF92ZXJ0aWNhbCcsIGVuZCA9ICdlbmQnLCBmaWxsID0gJ2ZpbGwnLCBmaWxsX2hvcml6b250YWwgPSAnZmlsbF9ob3Jpem9udGFsJywgZmlsbF92ZXJ0aWNhbCA9ICdmaWxsX3ZlcnRpY2FsJywgbGVmdCA9ICdsZWZ0JywgcmlnaHQgPSAncmlnaHQnLCBzdGFydCA9ICdzdGFydCcsIHRvcCA9ICd0b3AnIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0SGVpZ2h0RW51bSB7IGZpbGxfcGFyZW50ID0gJ2ZpbGxfcGFyZW50JywgbWF0Y2hfcGFyZW50ID0gJ21hdGNoX3BhcmVudCcsIHdyYXBfY29udGVudCA9ICd3cmFwX2NvbnRlbnQnIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0TW9kZUVudW0geyBjbGlwQm91bmRzID0gJ2NsaXBCb3VuZHMnLCBvcHRpY2FsQm91bmRzID0gJ29wdGljYWxCb3VuZHMnIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0V2lkdGhFbnVtIHsgZmlsbF9wYXJlbnQgPSAnZmlsbF9wYXJlbnQnLCBtYXRjaF9wYXJlbnQgPSAnbWF0Y2hfcGFyZW50Jywgd3JhcF9jb250ZW50ID0gJ3dyYXBfY29udGVudCcgfVxuICBleHBvcnQgaW50ZXJmYWNlIExpbmVhckxheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgICBiYXNlbGluZUFsaWduZWQ/OiBib29sZWFuO1xuICAgIGJhc2VsaW5lQWxpZ25lZENoaWxkSW5kZXg/OiBudW1iZXI7XG4gICAgZGl2aWRlcj86IG51bWJlcjtcbiAgICBkaXZpZGVyUGFkZGluZz86IHN0cmluZztcbiAgICBncmF2aXR5PzogR3Jhdml0eUZsYWdzRW51bV9fW107XG4gICAgbWVhc3VyZVdpdGhMYXJnZXN0Q2hpbGQ/OiBib29sZWFuO1xuICAgIG9yaWVudGF0aW9uPzogT3JpZW50YXRpb25FbnVtO1xuICAgIHNob3dEaXZpZGVycz86IFNob3dEaXZpZGVyc0ZsYWdzRW51bVtdO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgTGluZWFyTGF5b3V0TGF5b3V0UGFyYW1zQXR0cmlidXRlcyBleHRlbmRzIFZpZXdHcm91cE1hcmdpbkxheW91dFBhcmFtc0F0dHJpYnV0ZXMge1xuICAgIGxheW91dF9ncmF2aXR5PzogbnVtYmVyIHwgTGF5b3V0R3Jhdml0eUZsYWdzRW51bV9bXTtcbiAgICBsYXlvdXRfd2VpZ2h0PzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgTGlzdFZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgQWJzTGlzdFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBkaXZpZGVyPzogc3RyaW5nO1xuICAgIGRpdmlkZXJIZWlnaHQ/OiBzdHJpbmc7XG4gICAgZm9vdGVyRGl2aWRlcnNFbmFibGVkPzogYm9vbGVhbjtcbiAgICBoZWFkZXJEaXZpZGVyc0VuYWJsZWQ/OiBib29sZWFuO1xuICAgIG92ZXJTY3JvbGxGb290ZXI/OiBzdHJpbmc7XG4gICAgb3ZlclNjcm9sbEhlYWRlcj86IHN0cmluZztcbiAgfVxuICBleHBvcnQgZW51bSBNYXJxdWVlUmVwZWF0TGltaXRFbnVtIHsgbWFycXVlZV9mb3JldmVyID0gJ21hcnF1ZWVfZm9yZXZlcicgfVxuICBleHBvcnQgaW50ZXJmYWNlIE1lZGlhQ29udHJvbGxlckF0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgTXVsdGlBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBlbnVtIE51bUNvbHVtbnNFbnVtIHsgYXV0b19maXQgPSAnYXV0b19maXQnIH1cbiAgZXhwb3J0IGludGVyZmFjZSBOdW1iZXJQaWNrZXJBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gICAgaW50ZXJuYWxNaW5IZWlnaHQ/OiBzdHJpbmc7XG4gICAgaW50ZXJuYWxNaW5XaWR0aD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgZW51bSBPcmllbnRhdGlvbkVudW0geyBob3Jpem9udGFsID0gJ2hvcml6b250YWwnLCB2ZXJ0aWNhbCA9ICd2ZXJ0aWNhbCcgfVxuICBleHBvcnQgZW51bSBPcmllbnRhdGlvbkVudW1fIHsgaG9yaXpvbnRhbCA9ICdob3Jpem9udGFsJywgdmVydGljYWwgPSAndmVydGljYWwnIH1cbiAgZXhwb3J0IGVudW0gT3ZlclNjcm9sbE1vZGVFbnVtIHsgYWx3YXlzID0gJ2Fsd2F5cycsIGlmQ29udGVudFNjcm9sbHMgPSAnaWZDb250ZW50U2Nyb2xscycsIG5ldmVyID0gJ25ldmVyJyB9XG4gIGV4cG9ydCBlbnVtIFBlcnNpc3RlbnREcmF3aW5nQ2FjaGVGbGFnc0VudW0geyBhbGwgPSAnYWxsJywgYW5pbWF0aW9uID0gJ2FuaW1hdGlvbicsIG5vbmUgPSAnbm9uZScsIHNjcm9sbGluZyA9ICdzY3JvbGxpbmcnIH1cbiAgZXhwb3J0IGVudW0gUHJvZ3Jlc3NCYWNrZ3JvdW5kVGludE1vZGVFbnVtIHsgYWRkID0gJ2FkZCcsIG11bHRpcGx5ID0gJ211bHRpcGx5Jywgc2NyZWVuID0gJ3NjcmVlbicsIHNyY19hdG9wID0gJ3NyY19hdG9wJywgc3JjX2luID0gJ3NyY19pbicsIHNyY19vdmVyID0gJ3NyY19vdmVyJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgUHJvZ3Jlc3NCYXJBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICAgIGluZGV0ZXJtaW5hdGU/OiBib29sZWFuO1xuICAgIGluZGV0ZXJtaW5hdGVEcmF3YWJsZT86IHN0cmluZztcbiAgICBpbmRldGVybWluYXRlVGludD86IHN0cmluZztcbiAgICBpbmRldGVybWluYXRlVGludE1vZGU/OiBJbmRldGVybWluYXRlVGludE1vZGVFbnVtO1xuICAgIGludGVycG9sYXRvcj86IHN0cmluZztcbiAgICBtYXg/OiBudW1iZXI7XG4gICAgbWluPzogbnVtYmVyO1xuICAgIHByb2dyZXNzPzogbnVtYmVyO1xuICAgIHByb2dyZXNzQmFja2dyb3VuZFRpbnQ/OiBzdHJpbmc7XG4gICAgcHJvZ3Jlc3NCYWNrZ3JvdW5kVGludE1vZGU/OiBQcm9ncmVzc0JhY2tncm91bmRUaW50TW9kZUVudW07XG4gICAgcHJvZ3Jlc3NEcmF3YWJsZT86IHN0cmluZztcbiAgICBwcm9ncmVzc1RpbnQ/OiBzdHJpbmc7XG4gICAgcHJvZ3Jlc3NUaW50TW9kZT86IFByb2dyZXNzVGludE1vZGVFbnVtO1xuICAgIHNlY29uZGFyeVByb2dyZXNzPzogbnVtYmVyO1xuICAgIHNlY29uZGFyeVByb2dyZXNzVGludD86IHN0cmluZztcbiAgICBzZWNvbmRhcnlQcm9ncmVzc1RpbnRNb2RlPzogU2Vjb25kYXJ5UHJvZ3Jlc3NUaW50TW9kZUVudW07XG4gIH1cbiAgZXhwb3J0IGVudW0gUHJvZ3Jlc3NUaW50TW9kZUVudW0geyBhZGQgPSAnYWRkJywgbXVsdGlwbHkgPSAnbXVsdGlwbHknLCBzY3JlZW4gPSAnc2NyZWVuJywgc3JjX2F0b3AgPSAnc3JjX2F0b3AnLCBzcmNfaW4gPSAnc3JjX2luJywgc3JjX292ZXIgPSAnc3JjX292ZXInIH1cbiAgZXhwb3J0IGludGVyZmFjZSBRdWlja0NvbnRhY3RCYWRnZUF0dHJpYnV0ZXMgZXh0ZW5kcyBJbWFnZVZpZXdBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFJhZGlvQnV0dG9uQXR0cmlidXRlcyBleHRlbmRzIENvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBSYWRpb0dyb3VwQXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgUmFkaW9Hcm91cExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFJhdGluZ0JhckF0dHJpYnV0ZXMgZXh0ZW5kcyBBYnNTZWVrQmFyQXR0cmlidXRlcyB7XG4gICAgaXNJbmRpY2F0b3I/OiBib29sZWFuO1xuICAgIG51bVN0YXJzPzogbnVtYmVyO1xuICAgIHJhdGluZz86IG51bWJlcjtcbiAgICBzdGVwU2l6ZT86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFJlbGF0aXZlTGF5b3V0QXR0cmlidXRlcyBleHRlbmRzIFZpZXdHcm91cEF0dHJpYnV0ZXMge1xuICAgIGdyYXZpdHk/OiBHcmF2aXR5RmxhZ3NFbnVtX1tdO1xuICAgIGlnbm9yZUdyYXZpdHk/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBSZWxhdGl2ZUxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBTY2FsZVR5cGVFbnVtIHsgY2VudGVyID0gJ2NlbnRlcicsIGNlbnRlckNyb3AgPSAnY2VudGVyQ3JvcCcsIGNlbnRlckluc2lkZSA9ICdjZW50ZXJJbnNpZGUnLCBmaXRDZW50ZXIgPSAnZml0Q2VudGVyJywgZml0RW5kID0gJ2ZpdEVuZCcsIGZpdFN0YXJ0ID0gJ2ZpdFN0YXJ0JywgZml0WFkgPSAnZml0WFknLCBtYXRyaXggPSAnbWF0cml4JyB9XG4gIGV4cG9ydCBlbnVtIFNjcm9sbEluZGljYXRvcnNGbGFnc0VudW0geyBib3R0b20gPSAnYm90dG9tJywgZW5kID0gJ2VuZCcsIGxlZnQgPSAnbGVmdCcsIG5vbmUgPSAnbm9uZScsIHJpZ2h0ID0gJ3JpZ2h0Jywgc3RhcnQgPSAnc3RhcnQnLCB0b3AgPSAndG9wJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU2Nyb2xsVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICAgIGZpbGxWaWV3cG9ydD86IGJvb2xlYW47XG4gIH1cbiAgZXhwb3J0IGVudW0gU2Nyb2xsYmFyU3R5bGVFbnVtIHsgaW5zaWRlSW5zZXQgPSAnaW5zaWRlSW5zZXQnLCBpbnNpZGVPdmVybGF5ID0gJ2luc2lkZU92ZXJsYXknLCBvdXRzaWRlSW5zZXQgPSAnb3V0c2lkZUluc2V0Jywgb3V0c2lkZU92ZXJsYXkgPSAnb3V0c2lkZU92ZXJsYXknIH1cbiAgZXhwb3J0IGludGVyZmFjZSBTZWFyY2hWaWV3QXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dEF0dHJpYnV0ZXMge1xuICAgIGljb25pZmllZEJ5RGVmYXVsdD86IGJvb2xlYW47XG4gICAgaW1lT3B0aW9ucz86IG51bWJlcjtcbiAgICBpbnB1dFR5cGU/OiBudW1iZXI7XG4gICAgbWF4V2lkdGg/OiBzdHJpbmc7XG4gICAgcXVlcnlIaW50Pzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBlbnVtIFNlY29uZGFyeVByb2dyZXNzVGludE1vZGVFbnVtIHsgYWRkID0gJ2FkZCcsIG11bHRpcGx5ID0gJ211bHRpcGx5Jywgc2NyZWVuID0gJ3NjcmVlbicsIHNyY19hdG9wID0gJ3NyY19hdG9wJywgc3JjX2luID0gJ3NyY19pbicsIHNyY19vdmVyID0gJ3NyY19vdmVyJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU2Vla0JhckF0dHJpYnV0ZXMgZXh0ZW5kcyBBYnNTZWVrQmFyQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGVudW0gU2hvd0RpdmlkZXJzRmxhZ3NFbnVtIHsgYmVnaW5uaW5nID0gJ2JlZ2lubmluZycsIGVuZCA9ICdlbmQnLCBtaWRkbGUgPSAnbWlkZGxlJywgbm9uZSA9ICdub25lJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU3BhY2VBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU3Bpbm5lckF0dHJpYnV0ZXMgZXh0ZW5kcyBBYnNTcGlubmVyQXR0cmlidXRlcyB7XG4gICAgZHJvcERvd25XaWR0aD86IG51bWJlcjtcbiAgICBncmF2aXR5PzogR3Jhdml0eUZsYWdzRW51bV9fX1tdO1xuICAgIHBvcHVwQmFja2dyb3VuZD86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFN0YWNrVmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyBBZGFwdGVyVmlld0FuaW1hdG9yQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGVudW0gU3RyZXRjaE1vZGVFbnVtIHsgY29sdW1uV2lkdGggPSAnY29sdW1uV2lkdGgnLCBub25lID0gJ25vbmUnLCBzcGFjaW5nV2lkdGggPSAnc3BhY2luZ1dpZHRoJywgc3BhY2luZ1dpZHRoVW5pZm9ybSA9ICdzcGFjaW5nV2lkdGhVbmlmb3JtJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU3VyZmFjZVZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgU3dpdGNoQXR0cmlidXRlcyBleHRlbmRzIENvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyB7XG4gICAgc2hvd1RleHQ/OiBib29sZWFuO1xuICAgIHNwbGl0VHJhY2s/OiBib29sZWFuO1xuICAgIHN3aXRjaE1pbldpZHRoPzogc3RyaW5nO1xuICAgIHN3aXRjaFBhZGRpbmc/OiBzdHJpbmc7XG4gICAgc3dpdGNoVGV4dEFwcGVhcmFuY2U/OiBzdHJpbmc7XG4gICAgdGV4dE9mZj86IHN0cmluZztcbiAgICB0ZXh0T24/OiBzdHJpbmc7XG4gICAgdGh1bWI/OiBudW1iZXI7XG4gICAgdGh1bWJUZXh0UGFkZGluZz86IHN0cmluZztcbiAgICB0aHVtYlRpbnQ/OiBudW1iZXI7XG4gICAgdGh1bWJUaW50TW9kZT86IG51bWJlcjtcbiAgICB0cmFjaz86IHN0cmluZztcbiAgICB0cmFja1RpbnQ/OiBzdHJpbmc7XG4gICAgdHJhY2tUaW50TW9kZT86IFRyYWNrVGludE1vZGVFbnVtO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGFiSG9zdEF0dHJpYnV0ZXMgZXh0ZW5kcyBGcmFtZUxheW91dEF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGFiV2lkZ2V0QXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dEF0dHJpYnV0ZXMge1xuICAgIHRhYlN0cmlwRW5hYmxlZD86IGJvb2xlYW47XG4gICAgdGFiU3RyaXBMZWZ0Pzogc3RyaW5nO1xuICAgIHRhYlN0cmlwUmlnaHQ/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBUYWJsZUxheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRhYmxlTGF5b3V0TGF5b3V0UGFyYW1zQXR0cmlidXRlcyBleHRlbmRzIExpbmVhckxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGFibGVSb3dBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBUYWJsZVJvd0xheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBMaW5lYXJMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBUYWJsZVJvd19DZWxsX2xheW91dF9jb2x1bW4/OiBudW1iZXI7XG4gICAgVGFibGVSb3dfQ2VsbF9sYXlvdXRfc3Bhbj86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgZW51bSBUZXh0QWxpZ25tZW50RW51bSB7IGNlbnRlciA9ICdjZW50ZXInLCBncmF2aXR5ID0gJ2dyYXZpdHknLCBpbmhlcml0ID0gJ2luaGVyaXQnLCB0ZXh0RW5kID0gJ3RleHRFbmQnLCB0ZXh0U3RhcnQgPSAndGV4dFN0YXJ0Jywgdmlld0VuZCA9ICd2aWV3RW5kJywgdmlld1N0YXJ0ID0gJ3ZpZXdTdGFydCcgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRleHRDbG9ja0F0dHJpYnV0ZXMgZXh0ZW5kcyBUZXh0Vmlld0F0dHJpYnV0ZXMge1xuICAgIGZvcm1hdDEySG91cj86IHN0cmluZztcbiAgICBmb3JtYXQyNEhvdXI/OiBzdHJpbmc7XG4gICAgdGltZVpvbmU/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGVudW0gVGV4dERpcmVjdGlvbkVudW0geyBhbnlSdGwgPSAnYW55UnRsJywgZmlyc3RTdHJvbmcgPSAnZmlyc3RTdHJvbmcnLCBmaXJzdFN0cm9uZ0x0ciA9ICdmaXJzdFN0cm9uZ0x0cicsIGZpcnN0U3Ryb25nUnRsID0gJ2ZpcnN0U3Ryb25nUnRsJywgaW5oZXJpdCA9ICdpbmhlcml0JywgbG9jYWxlID0gJ2xvY2FsZScsIGx0ciA9ICdsdHInLCBydGwgPSAncnRsJyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGV4dFN3aXRjaGVyQXR0cmlidXRlcyBleHRlbmRzIFZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMge1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVGV4dFZpZXdBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0F0dHJpYnV0ZXMge1xuICAgIFZpZXdfY2xpY2thYmxlPzogYm9vbGVhbjtcbiAgICBWaWV3X2xvbmdDbGlja2FibGU/OiBib29sZWFuO1xuICAgIGF1dG9MaW5rPzogQXV0b0xpbmtGbGFnc0VudW1bXTtcbiAgICBhdXRvU2l6ZVRleHRUeXBlPzogQXV0b1NpemVUZXh0VHlwZUVudW07XG4gICAgYnJlYWtTdHJhdGVneT86IEJyZWFrU3RyYXRlZ3lFbnVtO1xuICAgIGN1cnNvclZpc2libGU/OiBib29sZWFuO1xuICAgIGRyYXdhYmxlQm90dG9tPzogc3RyaW5nO1xuICAgIGRyYXdhYmxlTGVmdD86IHN0cmluZztcbiAgICBkcmF3YWJsZVBhZGRpbmc/OiBzdHJpbmc7XG4gICAgZHJhd2FibGVSaWdodD86IHN0cmluZztcbiAgICBkcmF3YWJsZVRpbnQ/OiBzdHJpbmc7XG4gICAgZHJhd2FibGVUaW50TW9kZT86IERyYXdhYmxlVGludE1vZGVFbnVtO1xuICAgIGRyYXdhYmxlVG9wPzogc3RyaW5nO1xuICAgIGVkaXRvckV4dHJhcz86IHN0cmluZztcbiAgICBlbGVnYW50VGV4dEhlaWdodD86IGJvb2xlYW47XG4gICAgZWxsaXBzaXplPzogRWxsaXBzaXplRW51bTtcbiAgICBlbXM/OiBudW1iZXI7XG4gICAgZW5hYmxlZD86IGJvb2xlYW47XG4gICAgZmFsbGJhY2tMaW5lU3BhY2luZz86IGJvb2xlYW47XG4gICAgZmlyc3RCYXNlbGluZVRvVG9wSGVpZ2h0Pzogc3RyaW5nO1xuICAgIGZvbnRGZWF0dXJlU2V0dGluZ3M/OiBzdHJpbmc7XG4gICAgZnJlZXplc1RleHQ/OiBib29sZWFuO1xuICAgIGdyYXZpdHk/OiBHcmF2aXR5RmxhZ3NFbnVtW107XG4gICAgaGVpZ2h0Pzogc3RyaW5nO1xuICAgIGhpbnQ/OiBzdHJpbmc7XG4gICAgaHlwaGVuYXRpb25GcmVxdWVuY3k/OiBIeXBoZW5hdGlvbkZyZXF1ZW5jeUVudW07XG4gICAgaW1lT3B0aW9ucz86IEltZU9wdGlvbnNGbGFnc0VudW1bXTtcbiAgICBpbmNsdWRlRm9udFBhZGRpbmc/OiBib29sZWFuO1xuICAgIGlucHV0VHlwZT86IElucHV0VHlwZUZsYWdzRW51bVtdO1xuICAgIGp1c3RpZmljYXRpb25Nb2RlPzogSnVzdGlmaWNhdGlvbk1vZGVFbnVtO1xuICAgIGxhc3RCYXNlbGluZVRvQm90dG9tSGVpZ2h0Pzogc3RyaW5nO1xuICAgIGxldHRlclNwYWNpbmc/OiBudW1iZXI7XG4gICAgbGluZUhlaWdodD86IHN0cmluZztcbiAgICBsaW5lcz86IG51bWJlcjtcbiAgICBsaW5rc0NsaWNrYWJsZT86IGJvb2xlYW47XG4gICAgbWFycXVlZVJlcGVhdExpbWl0PzogbnVtYmVyIHwgTWFycXVlZVJlcGVhdExpbWl0RW51bTtcbiAgICBtYXhFbXM/OiBudW1iZXI7XG4gICAgbWF4SGVpZ2h0Pzogc3RyaW5nO1xuICAgIG1heExpbmVzPzogbnVtYmVyO1xuICAgIG1heFdpZHRoPzogc3RyaW5nO1xuICAgIG1pbkVtcz86IG51bWJlcjtcbiAgICBtaW5MaW5lcz86IG51bWJlcjtcbiAgICBwcml2YXRlSW1lT3B0aW9ucz86IHN0cmluZztcbiAgICBzaGFkb3dDb2xvcj86IHN0cmluZztcbiAgICBzaGFkb3dEeD86IG51bWJlcjtcbiAgICBzaGFkb3dEeT86IG51bWJlcjtcbiAgICBzaGFkb3dSYWRpdXM/OiBudW1iZXI7XG4gICAgdGV4dD86IHN0cmluZztcbiAgICB0ZXh0QWxsQ2Fwcz86IGJvb2xlYW47XG4gICAgdGV4dENvbG9yPzogc3RyaW5nO1xuICAgIHRleHRDb2xvckhpZ2hsaWdodD86IHN0cmluZztcbiAgICB0ZXh0Q29sb3JIaW50Pzogc3RyaW5nO1xuICAgIHRleHRDb2xvckxpbms/OiBzdHJpbmc7XG4gICAgdGV4dElzU2VsZWN0YWJsZT86IGJvb2xlYW47XG4gICAgdGV4dFNjYWxlWD86IG51bWJlcjtcbiAgICB0ZXh0U2l6ZT86IHN0cmluZztcbiAgICB3aWR0aD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRleHR1cmVWaWV3QXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBUaHVtYlRpbnRNb2RlRW51bSB7IGFkZCA9ICdhZGQnLCBtdWx0aXBseSA9ICdtdWx0aXBseScsIHNjcmVlbiA9ICdzY3JlZW4nLCBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsIHNyY19pbiA9ICdzcmNfaW4nLCBzcmNfb3ZlciA9ICdzcmNfb3ZlcicgfVxuICBleHBvcnQgZW51bSBUaWNrTWFya1RpbnRNb2RlRW51bSB7IGFkZCA9ICdhZGQnLCBtdWx0aXBseSA9ICdtdWx0aXBseScsIHNjcmVlbiA9ICdzY3JlZW4nLCBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsIHNyY19pbiA9ICdzcmNfaW4nLCBzcmNfb3ZlciA9ICdzcmNfb3ZlcicgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRpbWVQaWNrZXJBdHRyaWJ1dGVzIGV4dGVuZHMgRnJhbWVMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFRvZ2dsZUJ1dHRvbkF0dHJpYnV0ZXMgZXh0ZW5kcyBDb21wb3VuZEJ1dHRvbkF0dHJpYnV0ZXMge1xuICAgIHRleHRPZmY/OiBzdHJpbmc7XG4gICAgdGV4dE9uPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVG9vbGJhckF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgICBjb250ZW50SW5zZXRFbmQ/OiBzdHJpbmc7XG4gICAgY29udGVudEluc2V0RW5kV2l0aEFjdGlvbnM/OiBzdHJpbmc7XG4gICAgY29udGVudEluc2V0TGVmdD86IHN0cmluZztcbiAgICBjb250ZW50SW5zZXRSaWdodD86IHN0cmluZztcbiAgICBjb250ZW50SW5zZXRTdGFydD86IHN0cmluZztcbiAgICBjb250ZW50SW5zZXRTdGFydFdpdGhOYXZpZ2F0aW9uPzogc3RyaW5nO1xuICAgIGxvZ28/OiBudW1iZXI7XG4gICAgbG9nb0Rlc2NyaXB0aW9uPzogc3RyaW5nO1xuICAgIG5hdmlnYXRpb25Db250ZW50RGVzY3JpcHRpb24/OiBzdHJpbmc7XG4gICAgbmF2aWdhdGlvbkljb24/OiBzdHJpbmc7XG4gICAgcG9wdXBUaGVtZT86IG51bWJlcjtcbiAgICBzdWJ0aXRsZT86IHN0cmluZztcbiAgICBzdWJ0aXRsZVRleHRDb2xvcj86IHN0cmluZztcbiAgICB0aXRsZT86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpbj86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpbkJvdHRvbT86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpbkVuZD86IHN0cmluZztcbiAgICB0aXRsZU1hcmdpblN0YXJ0Pzogc3RyaW5nO1xuICAgIHRpdGxlTWFyZ2luVG9wPzogc3RyaW5nO1xuICAgIHRpdGxlVGV4dENvbG9yPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVG9vbGJhckxheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3R3JvdXBMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBUcmFja1RpbnRNb2RlRW51bSB7IGFkZCA9ICdhZGQnLCBtdWx0aXBseSA9ICdtdWx0aXBseScsIHNjcmVlbiA9ICdzY3JlZW4nLCBzcmNfYXRvcCA9ICdzcmNfYXRvcCcsIHNyY19pbiA9ICdzcmNfaW4nLCBzcmNfb3ZlciA9ICdzcmNfb3ZlcicgfVxuICBleHBvcnQgZW51bSBUcmFuc2NyaXB0TW9kZUVudW0geyBhbHdheXNTY3JvbGwgPSAnYWx3YXlzU2Nyb2xsJywgZGlzYWJsZWQgPSAnZGlzYWJsZWQnLCBub3JtYWwgPSAnbm9ybWFsJyB9XG4gIGV4cG9ydCBlbnVtIFZlcnRpY2FsU2Nyb2xsYmFyUG9zaXRpb25FbnVtIHsgZGVmYXVsdFBvc2l0aW9uID0gJ2RlZmF1bHRQb3NpdGlvbicsIGxlZnQgPSAnbGVmdCcsIHJpZ2h0ID0gJ3JpZ2h0JyB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVmlkZW9WaWV3QXR0cmlidXRlcyBleHRlbmRzIFN1cmZhY2VWaWV3QXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzIGV4dGVuZHMgRnJhbWVMYXlvdXRBdHRyaWJ1dGVzIHtcbiAgICBGcmFtZUxheW91dF9tZWFzdXJlQWxsQ2hpbGRyZW4/OiBib29sZWFuO1xuICAgIGFuaW1hdGVGaXJzdFZpZXc/OiBib29sZWFuO1xuICAgIGluQW5pbWF0aW9uPzogc3RyaW5nO1xuICAgIG91dEFuaW1hdGlvbj86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBhY2Nlc3NpYmlsaXR5SGVhZGluZz86IGJvb2xlYW47XG4gICAgYWNjZXNzaWJpbGl0eUxpdmVSZWdpb24/OiBudW1iZXIgfCBBY2Nlc3NpYmlsaXR5TGl2ZVJlZ2lvbkVudW07XG4gICAgYWNjZXNzaWJpbGl0eVBhbmVUaXRsZT86IHN0cmluZztcbiAgICBhY2Nlc3NpYmlsaXR5VHJhdmVyc2FsQWZ0ZXI/OiBudW1iZXI7XG4gICAgYWNjZXNzaWJpbGl0eVRyYXZlcnNhbEJlZm9yZT86IG51bWJlcjtcbiAgICBhbHBoYT86IG51bWJlcjtcbiAgICBhdXRvZmlsbEhpbnRzPzogc3RyaW5nO1xuICAgIGJhY2tncm91bmQ/OiBzdHJpbmc7XG4gICAgYmFja2dyb3VuZFRpbnQ/OiBzdHJpbmc7XG4gICAgYmFja2dyb3VuZFRpbnRNb2RlPzogQmFja2dyb3VuZFRpbnRNb2RlRW51bTtcbiAgICBjbGlja2FibGU/OiBib29sZWFuO1xuICAgIGNvbnRlbnREZXNjcmlwdGlvbj86IHN0cmluZztcbiAgICBjb250ZXh0Q2xpY2thYmxlPzogYm9vbGVhbjtcbiAgICBkZWZhdWx0Rm9jdXNIaWdobGlnaHRFbmFibGVkPzogYm9vbGVhbjtcbiAgICBkcmF3aW5nQ2FjaGVRdWFsaXR5PzogRHJhd2luZ0NhY2hlUXVhbGl0eUVudW07XG4gICAgZWxldmF0aW9uPzogc3RyaW5nO1xuICAgIGZhZGVTY3JvbGxiYXJzPzogYm9vbGVhbjtcbiAgICBmaWx0ZXJUb3VjaGVzV2hlbk9ic2N1cmVkPzogYm9vbGVhbjtcbiAgICBmaXRzU3lzdGVtV2luZG93cz86IGJvb2xlYW47XG4gICAgZm9jdXNhYmxlPzogYm9vbGVhbiB8IEZvY3VzYWJsZUVudW07XG4gICAgZm9jdXNhYmxlSW5Ub3VjaE1vZGU/OiBib29sZWFuO1xuICAgIGZvY3VzZWRCeURlZmF1bHQ/OiBib29sZWFuO1xuICAgIGZvcmNlSGFzT3ZlcmxhcHBpbmdSZW5kZXJpbmc/OiBib29sZWFuO1xuICAgIGZvcmVncm91bmQ/OiBzdHJpbmc7XG4gICAgZm9yZWdyb3VuZEdyYXZpdHk/OiBGb3JlZ3JvdW5kR3Jhdml0eUZsYWdzRW51bVtdO1xuICAgIGZvcmVncm91bmRUaW50Pzogc3RyaW5nO1xuICAgIGZvcmVncm91bmRUaW50TW9kZT86IEZvcmVncm91bmRUaW50TW9kZUVudW07XG4gICAgaGFwdGljRmVlZGJhY2tFbmFibGVkPzogYm9vbGVhbjtcbiAgICBpZD86IHN0cmluZztcbiAgICBpbXBvcnRhbnRGb3JBY2Nlc3NpYmlsaXR5PzogbnVtYmVyIHwgSW1wb3J0YW50Rm9yQWNjZXNzaWJpbGl0eUVudW07XG4gICAgaW1wb3J0YW50Rm9yQXV0b2ZpbGw/OiBJbXBvcnRhbnRGb3JBdXRvZmlsbEZsYWdzRW51bVtdO1xuICAgIGlzU2Nyb2xsQ29udGFpbmVyPzogYm9vbGVhbjtcbiAgICBrZWVwU2NyZWVuT24/OiBib29sZWFuO1xuICAgIGtleWJvYXJkTmF2aWdhdGlvbkNsdXN0ZXI/OiBib29sZWFuO1xuICAgIGxhYmVsRm9yPzogc3RyaW5nO1xuICAgIGxheWVyVHlwZT86IExheWVyVHlwZUVudW07XG4gICAgbGF5b3V0RGlyZWN0aW9uPzogTGF5b3V0RGlyZWN0aW9uRW51bTtcbiAgICBsb25nQ2xpY2thYmxlPzogYm9vbGVhbjtcbiAgICBtaW5IZWlnaHQ/OiBzdHJpbmc7XG4gICAgbWluV2lkdGg/OiBzdHJpbmc7XG4gICAgbmVzdGVkU2Nyb2xsaW5nRW5hYmxlZD86IGJvb2xlYW47XG4gICAgbmV4dENsdXN0ZXJGb3J3YXJkPzogc3RyaW5nO1xuICAgIG5leHRGb2N1c0Rvd24/OiBzdHJpbmc7XG4gICAgbmV4dEZvY3VzRm9yd2FyZD86IHN0cmluZztcbiAgICBuZXh0Rm9jdXNMZWZ0Pzogc3RyaW5nO1xuICAgIG5leHRGb2N1c1JpZ2h0Pzogc3RyaW5nO1xuICAgIG5leHRGb2N1c1VwPzogc3RyaW5nO1xuICAgIG91dGxpbmVBbWJpZW50U2hhZG93Q29sb3I/OiBzdHJpbmc7XG4gICAgb3V0bGluZVNwb3RTaGFkb3dDb2xvcj86IHN0cmluZztcbiAgICBvdmVyU2Nyb2xsTW9kZT86IE92ZXJTY3JvbGxNb2RlRW51bTtcbiAgICBwYWRkaW5nQm90dG9tPzogc3RyaW5nO1xuICAgIHBhZGRpbmdFbmQ/OiBzdHJpbmc7XG4gICAgcGFkZGluZ0xlZnQ/OiBzdHJpbmc7XG4gICAgcGFkZGluZ1JpZ2h0Pzogc3RyaW5nO1xuICAgIHBhZGRpbmdTdGFydD86IHN0cmluZztcbiAgICBwYWRkaW5nVG9wPzogc3RyaW5nO1xuICAgIHJvdGF0aW9uPzogbnVtYmVyO1xuICAgIHJvdGF0aW9uWD86IG51bWJlcjtcbiAgICByb3RhdGlvblk/OiBudW1iZXI7XG4gICAgc2F2ZUVuYWJsZWQ/OiBib29sZWFuO1xuICAgIHNjYWxlWD86IG51bWJlcjtcbiAgICBzY2FsZVk/OiBudW1iZXI7XG4gICAgc2NyZWVuUmVhZGVyRm9jdXNhYmxlPzogYm9vbGVhbjtcbiAgICBzY3JvbGxJbmRpY2F0b3JzPzogU2Nyb2xsSW5kaWNhdG9yc0ZsYWdzRW51bVtdO1xuICAgIHNjcm9sbFg/OiBzdHJpbmc7XG4gICAgc2Nyb2xsWT86IHN0cmluZztcbiAgICBzY3JvbGxiYXJEZWZhdWx0RGVsYXlCZWZvcmVGYWRlPzogbnVtYmVyO1xuICAgIHNjcm9sbGJhckZhZGVEdXJhdGlvbj86IG51bWJlcjtcbiAgICBzY3JvbGxiYXJTaXplPzogc3RyaW5nO1xuICAgIHNjcm9sbGJhclN0eWxlPzogU2Nyb2xsYmFyU3R5bGVFbnVtO1xuICAgIHNvdW5kRWZmZWN0c0VuYWJsZWQ/OiBib29sZWFuO1xuICAgIHRhZz86IHN0cmluZztcbiAgICB0ZXh0QWxpZ25tZW50PzogbnVtYmVyIHwgVGV4dEFsaWdubWVudEVudW07XG4gICAgdGV4dERpcmVjdGlvbj86IG51bWJlciB8IFRleHREaXJlY3Rpb25FbnVtO1xuICAgIHRvb2x0aXBUZXh0Pzogc3RyaW5nO1xuICAgIHRyYW5zZm9ybVBpdm90WD86IHN0cmluZztcbiAgICB0cmFuc2Zvcm1QaXZvdFk/OiBzdHJpbmc7XG4gICAgdHJhbnNpdGlvbk5hbWU/OiBzdHJpbmc7XG4gICAgdHJhbnNsYXRpb25YPzogc3RyaW5nO1xuICAgIHRyYW5zbGF0aW9uWT86IHN0cmluZztcbiAgICB0cmFuc2xhdGlvblo/OiBzdHJpbmc7XG4gICAgdmVydGljYWxTY3JvbGxiYXJQb3NpdGlvbj86IFZlcnRpY2FsU2Nyb2xsYmFyUG9zaXRpb25FbnVtO1xuICAgIHZpc2liaWxpdHk/OiBWaXNpYmlsaXR5RW51bTtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdGbGlwcGVyQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBbmltYXRvckF0dHJpYnV0ZXMge1xuICAgIGF1dG9TdGFydD86IGJvb2xlYW47XG4gICAgZmxpcEludGVydmFsPzogbnVtYmVyO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgVmlld0dyb3VwQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBhZGRTdGF0ZXNGcm9tQ2hpbGRyZW4/OiBib29sZWFuO1xuICAgIGFsd2F5c0RyYXduV2l0aENhY2hlPzogYm9vbGVhbjtcbiAgICBhbmltYXRlTGF5b3V0Q2hhbmdlcz86IGJvb2xlYW47XG4gICAgYW5pbWF0aW9uQ2FjaGU/OiBib29sZWFuO1xuICAgIGNsaXBDaGlsZHJlbj86IGJvb2xlYW47XG4gICAgY2xpcFRvUGFkZGluZz86IGJvb2xlYW47XG4gICAgbGF5b3V0TW9kZT86IExheW91dE1vZGVFbnVtO1xuICAgIHBlcnNpc3RlbnREcmF3aW5nQ2FjaGU/OiBQZXJzaXN0ZW50RHJhd2luZ0NhY2hlRmxhZ3NFbnVtW107XG4gICAgc3BsaXRNb3Rpb25FdmVudHM/OiBib29sZWFuO1xuICAgIHRvdWNoc2NyZWVuQmxvY2tzRm9jdXM/OiBib29sZWFuO1xuICAgIHRyYW5zaXRpb25Hcm91cD86IGJvb2xlYW47XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBWaWV3R3JvdXBMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBsYXlvdXRfaGVpZ2h0Pzogc3RyaW5nIHwgTGF5b3V0SGVpZ2h0RW51bTtcbiAgICBsYXlvdXRfd2lkdGg/OiBzdHJpbmcgfCBMYXlvdXRXaWR0aEVudW07XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBWaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgVmlld0dyb3VwTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gICAgbGF5b3V0X21hcmdpbkJvdHRvbT86IHN0cmluZztcbiAgICBsYXlvdXRfbWFyZ2luRW5kPzogc3RyaW5nO1xuICAgIGxheW91dF9tYXJnaW5MZWZ0Pzogc3RyaW5nO1xuICAgIGxheW91dF9tYXJnaW5SaWdodD86IHN0cmluZztcbiAgICBsYXlvdXRfbWFyZ2luU3RhcnQ/OiBzdHJpbmc7XG4gICAgbGF5b3V0X21hcmdpblRvcD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdTdHViQXR0cmlidXRlcyBleHRlbmRzIFZpZXdBdHRyaWJ1dGVzIHtcbiAgICBpbmZsYXRlZElkPzogc3RyaW5nO1xuICAgIGxheW91dD86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIFZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMgZXh0ZW5kcyBWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgZW51bSBWaXNpYmlsaXR5RW51bSB7IGdvbmUgPSAnZ29uZScsIGludmlzaWJsZSA9ICdpbnZpc2libGUnLCB2aXNpYmxlID0gJ3Zpc2libGUnIH1cbiAgZXhwb3J0IGludGVyZmFjZSBab29tQ29udHJvbHNBdHRyaWJ1dGVzIGV4dGVuZHMgTGluZWFyTGF5b3V0QXR0cmlidXRlcyB7XG4gIH1cbn1cbi8vIGVsZW1lbnRzXG5leHBvcnQgY29uc3QgQWRhcHRlclZpZXdBbmltYXRvciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BZGFwdGVyVmlld0FuaW1hdG9yQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkFkYXB0ZXJWaWV3QW5pbWF0b3JBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2FkYXB0ZXJWaWV3QW5pbWF0b3InLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEZyYW1lTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkZyYW1lTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkZyYW1lTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdmcmFtZUxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5WaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFNlYXJjaFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuU2VhcmNoVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5TZWFyY2hWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdzZWFyY2hWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUYWJsZVJvdyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5UYWJsZVJvd0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UYWJsZVJvd0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGFibGVSb3cnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFJlbGF0aXZlTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlJlbGF0aXZlTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlJlbGF0aXZlTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdyZWxhdGl2ZUxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgSW1hZ2VTd2l0Y2hlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5JbWFnZVN3aXRjaGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkltYWdlU3dpdGNoZXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2ltYWdlU3dpdGNoZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFJhZGlvR3JvdXAgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuUmFkaW9Hcm91cEF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5SYWRpb0dyb3VwQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdyYWRpb0dyb3VwJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUb29sYmFyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRvb2xiYXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVG9vbGJhckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndG9vbGJhcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgTGlzdFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuTGlzdFZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuTGlzdFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2xpc3RWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBTcGlubmVyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlNwaW5uZXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3Bpbm5lckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnc3Bpbm5lcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgU2Nyb2xsVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TY3JvbGxWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlNjcm9sbFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3Njcm9sbFZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFZpZXdGbGlwcGVyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlZpZXdGbGlwcGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlZpZXdGbGlwcGVyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd2aWV3RmxpcHBlcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlld1N3aXRjaGVyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlZpZXdTd2l0Y2hlckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5WaWV3U3dpdGNoZXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXdTd2l0Y2hlcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgRGF0ZVBpY2tlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5EYXRlUGlja2VyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkRhdGVQaWNrZXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2RhdGVQaWNrZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFN0YWNrVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TdGFja1ZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3RhY2tWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdzdGFja1ZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFRpbWVQaWNrZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGltZVBpY2tlckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UaW1lUGlja2VyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd0aW1lUGlja2VyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBNZWRpYUNvbnRyb2xsZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuTWVkaWFDb250cm9sbGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLk1lZGlhQ29udHJvbGxlckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnbWVkaWFDb250cm9sbGVyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBBY3Rpb25NZW51VmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BY3Rpb25NZW51Vmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5BY3Rpb25NZW51Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnYWN0aW9uTWVudVZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFpvb21Db250cm9scyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5ab29tQ29udHJvbHNBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuWm9vbUNvbnRyb2xzQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd6b29tQ29udHJvbHMnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEFkYXB0ZXJWaWV3ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkFkYXB0ZXJWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkFkYXB0ZXJWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhZGFwdGVyVmlldycsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlld0FuaW1hdG9yID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlZpZXdBbmltYXRvckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5WaWV3QW5pbWF0b3JBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXdBbmltYXRvcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgSG9yaXpvbnRhbFNjcm9sbFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuSG9yaXpvbnRhbFNjcm9sbFZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuSG9yaXpvbnRhbFNjcm9sbFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2hvcml6b250YWxTY3JvbGxWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDYWxlbmRhclZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuQ2FsZW5kYXJWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkNhbGVuZGFyVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2FsZW5kYXJWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBBYnNMaXN0VmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BYnNMaXN0Vmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5BYnNMaXN0Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnYWJzTGlzdFZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IExpbmVhckxheW91dCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5MaW5lYXJMYXlvdXRBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuTGluZWFyTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdsaW5lYXJMYXlvdXQnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IE51bWJlclBpY2tlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5OdW1iZXJQaWNrZXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuTnVtYmVyUGlja2VyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdudW1iZXJQaWNrZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEFkYXB0ZXJWaWV3RmxpcHBlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5BZGFwdGVyVmlld0ZsaXBwZXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuQWRhcHRlclZpZXdGbGlwcGVyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhZGFwdGVyVmlld0ZsaXBwZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEFic1NwaW5uZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuQWJzU3Bpbm5lckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5BYnNTcGlubmVyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhYnNTcGlubmVyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBWaWV3R3JvdXAgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVmlld0dyb3VwQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlZpZXdHcm91cEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndmlld0dyb3VwJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUZXh0U3dpdGNoZXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGV4dFN3aXRjaGVyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRleHRTd2l0Y2hlckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGV4dFN3aXRjaGVyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBFeHBhbmRhYmxlTGlzdFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuRXhwYW5kYWJsZUxpc3RWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkV4cGFuZGFibGVMaXN0Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnZXhwYW5kYWJsZUxpc3RWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBHcmlkVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5HcmlkVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5HcmlkVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnZ3JpZFZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFRhYmxlTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRhYmxlTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRhYmxlTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd0YWJsZUxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVGFiSG9zdCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5UYWJIb3N0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRhYkhvc3RBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RhYkhvc3QnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEdyaWRMYXlvdXQgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuR3JpZExheW91dEF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5HcmlkTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdncmlkTGF5b3V0JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUYWJXaWRnZXQgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGFiV2lkZ2V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlRhYldpZGdldEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGFiV2lkZ2V0JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBTcGFjZSA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TcGFjZUF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5TcGFjZUF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnc3BhY2UnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IE11bHRpQXV0b0NvbXBsZXRlVGV4dFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuTXVsdGlBdXRvQ29tcGxldGVUZXh0Vmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5NdWx0aUF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdtdWx0aUF1dG9Db21wbGV0ZVRleHRWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBRdWlja0NvbnRhY3RCYWRnZSA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5RdWlja0NvbnRhY3RCYWRnZUF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5RdWlja0NvbnRhY3RCYWRnZUF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgncXVpY2tDb250YWN0QmFkZ2UnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEVkaXRUZXh0ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkVkaXRUZXh0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkVkaXRUZXh0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdlZGl0VGV4dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgU3VyZmFjZVZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuU3VyZmFjZVZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3VyZmFjZVZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3N1cmZhY2VWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBSYXRpbmdCYXIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuUmF0aW5nQmFyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlJhdGluZ0JhckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgncmF0aW5nQmFyJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBJbWFnZVZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuSW1hZ2VWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkltYWdlVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnaW1hZ2VWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBBYnNTZWVrQmFyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkFic1NlZWtCYXJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuQWJzU2Vla0JhckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnYWJzU2Vla0JhcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVmlld1N0dWIgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVmlld1N0dWJBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVmlld1N0dWJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3ZpZXdTdHViJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDaGVja2VkVGV4dFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuQ2hlY2tlZFRleHRWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkNoZWNrZWRUZXh0Vmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2hlY2tlZFRleHRWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBUZXh0dXJlVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5UZXh0dXJlVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UZXh0dXJlVmlld0F0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgndGV4dHVyZVZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFByb2dyZXNzQmFyID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlByb2dyZXNzQmFyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlByb2dyZXNzQmFyQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdwcm9ncmVzc0JhcicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVGV4dFZpZXcgPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuVGV4dFZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVGV4dFZpZXdBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RleHRWaWV3JywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDaGVja0JveCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5DaGVja0JveEF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5DaGVja0JveEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2hlY2tCb3gnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFN3aXRjaCA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5Td2l0Y2hBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuU3dpdGNoQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdzd2l0Y2gnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFJhZGlvQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlJhZGlvQnV0dG9uQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlJhZGlvQnV0dG9uQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdyYWRpb0J1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgU2Vla0JhciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5TZWVrQmFyQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLlNlZWtCYXJBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3NlZWtCYXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IENvbXBvdW5kQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkNvbXBvdW5kQnV0dG9uQXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkNvbXBvdW5kQnV0dG9uQXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdjb21wb3VuZEJ1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVG9nZ2xlQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRvZ2dsZUJ1dHRvbkF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5Ub2dnbGVCdXR0b25BdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RvZ2dsZUJ1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgVGV4dENsb2NrID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLlRleHRDbG9ja0F0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5UZXh0Q2xvY2tBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ3RleHRDbG9jaycsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgSW1hZ2VCdXR0b24gPSAoXG4gIGF0dHJpYnV0ZXM/OiBNYWluVHlwZXMuSW1hZ2VCdXR0b25BdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuSW1hZ2VCdXR0b25BdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2ltYWdlQnV0dG9uJywgYXR0cmlidXRlcywgY2hpbGRyZW4pO1xufTtcbmV4cG9ydCBjb25zdCBDaHJvbm9tZXRlciA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5DaHJvbm9tZXRlckF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5DaHJvbm9tZXRlckF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY2hyb25vbWV0ZXInLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IFZpZGVvVmlldyA9IChcbiAgYXR0cmlidXRlcz86IE1haW5UeXBlcy5WaWRlb1ZpZXdBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxNYWluVHlwZXMuVmlkZW9WaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCd2aWRlb1ZpZXcnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59O1xuZXhwb3J0IGNvbnN0IEF1dG9Db21wbGV0ZVRleHRWaWV3ID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8TWFpblR5cGVzLkF1dG9Db21wbGV0ZVRleHRWaWV3QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdhdXRvQ29tcGxldGVUZXh0VmlldycsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07XG5leHBvcnQgY29uc3QgQnV0dG9uID0gKFxuICBhdHRyaWJ1dGVzPzogTWFpblR5cGVzLkJ1dHRvbkF0dHJpYnV0ZXMgJiBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMsXG4gIGNoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPj5cbik6IEVsZW1lbnROb2RlPE1haW5UeXBlcy5CdXR0b25BdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2J1dHRvbicsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07Il0sInZlcnNpb24iOjN9